package net.weg.eng.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.MaestroException;
import net.weg.eng.document.service.ObjectContextDocumentService;
import net.weg.eng.document.service.ObjectContextDocumentSipService;
import net.weg.eng.document.service.client.SgpServiceClient;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.util.RuleCompositeHelper;
import net.weg.maestro.tracemonitor.DebuggerMonitor;
import net.weg.maestro.tracemonitor.MessageLogType;
import net.weg.maestro.tracemonitor.TraceLevel;

public class DocumentViewController implements Controller {

	private static final String INLINE_FILENAME = "inline; filename=";
	private static final String APPLICATION = "application/";
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String RAW_PATH = "//brjgs100/dfsweg/APPS/_NONSTD/SGP/";
	public static final String SIPWMO_FORCED_SGP = "SIPWMO_FORCED_SGP";
	public static final String TEMPLATE_DOCUMENT_TYPE = "TEMPLATE_DOCUMENT_TYPE";
	public static final String SIM = "SIM";
	public static final String ZEQ = "ZEQ";
	public static final String ZID = "ZID";

	@Resource
	private ObjectContextDocumentService objectContextDocumentService;

	@Resource
	private SgpServiceClient sgpServiceClient;

	@Resource
	private ObjectContextTree objectContextTree;
	
	@Resource
	private ObjectContext objectContext;

	@Resource
	private ObjectContextDocumentSipService objectContextDocumentSipService;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String documentTemplateNode = URLDecoder.decode(request.getParameter("documentTemplateNode"),"utf-8");
			String documentType = request.getParameter("file");
			if(!"pdf".equals(documentType) && !(objectContext.getProfile().getGroups().contains("_GG_DFSWEG_APPS_MAESTRO_SIP_TEC") || objectContext.getProfile().getGroups().contains("_GG_DFSWEG_APPS_MAESTRO_ADM") || objectContext.getProfile().getGroups().contains("_GG_DFSWEG_APPS_MAESTRO_SIP_ADM")))
				throw new MaestroException("No Permission");
			if (!CheckHelper.isNullOrEmpty(documentType)) {
				checkDocumentType(response, documentTemplateNode, documentType);
			}
		} catch (Exception ex) {
			response.setContentType("text/plain");
			String message = ex.getMessage();
			if (message == null) {
				message = objectContext.getTranslateOrLabel("project_contains_errors");
			}
			response.addHeader(CONTENT_LENGTH, String.valueOf(message.length()));
			response.getWriter().write(message);
			response.getWriter().flush();
			response.getWriter().close();
		}
		return null;
	}

	private void checkDocumentType(HttpServletResponse response, String documentTemplateNode, String documentType) throws Exception {
		ObjectContext documentNodeContext = RuleCompositeHelper.searchObjectContextNode(objectContextTree.getRoot(), documentTemplateNode).get(0);
		byte[] content = null;
		objectContextDocumentSipService.processBeforeDocumentDownload(objectContextTree.getRoot(), documentType);
		if (documentNodeContext.get(SIPWMO_FORCED_SGP) != null && SIM.equals(documentNodeContext.get(SIPWMO_FORCED_SGP))) {
            generateDocumentFromSgp(response, documentTemplateNode, documentType);
        } else	if (ZEQ.equals(documentNodeContext.get(TEMPLATE_DOCUMENT_TYPE))) {
            content = objectContextDocumentService.generateThermalLabelDocument(documentNodeContext, documentType);
            configureResponse(response, documentType, content, documentTemplateNode);
        } else if (ZID.equals(documentNodeContext.get(TEMPLATE_DOCUMENT_TYPE))){
            content = objectContextDocumentService.generateTeighaDocument(documentNodeContext, documentType);
            configureResponse(response, documentType, content, documentTemplateNode);
        } else {
            generateDocumentFromSgp(response, documentTemplateNode, documentType);
        }
	}

	private void generateDocumentFromSgp(HttpServletResponse response, String documentTemplateNode, String documentType) throws Exception {
		byte[] content; 
		ObjectContext docContext = objectContextTree.getTreeHash().get(documentTemplateNode);
		String documentPath = Paths.get(docContext.get("DOCUMENT_PATH").toString()).toString();
		String path = new StringBuilder(Paths.get(RAW_PATH, documentPath).toString()).append(File.separator).append(docContext.get("DOCUMENT_FILENAME").toString()).append(".").append(documentType).toString();
		path = Paths.get(path).toString();
		DebuggerMonitor.getInstance().logString(MessageLogType.Execution, TraceLevel.Service, path);
		content = sgpServiceClient.getDocument(objectContextTree.getObjectContextRoot(), path);
		configureResponse(response, documentType, content, documentTemplateNode);

	}

	private void configureResponse(HttpServletResponse response, String documentType, byte[] content, String documentTemplateNode) throws IOException {
		response.setContentType(APPLICATION + documentType);
		response.addHeader(CONTENT_LENGTH, String.valueOf(content.length));
		response.addHeader(CONTENT_DISPOSITION, INLINE_FILENAME + documentTemplateNode + "." + documentType);
		response.getOutputStream().write(content, 0, content.length);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

}
