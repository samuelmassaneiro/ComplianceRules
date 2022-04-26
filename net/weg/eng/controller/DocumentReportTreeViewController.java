package net.weg.eng.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.service.report.ReportService;
import net.weg.iceberg.report.fop.FopReport;

public class DocumentReportTreeViewController implements Controller {

	
	@Resource
	private ObjectContextTree objectContextTree;
	
	@Resource
	private ReportService reportService; 


	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String documentTemplateNode = request.getParameter("template"); 
		 
		if (!CheckHelper.isNullOrEmpty(documentTemplateNode)) {
			FopReport report = (FopReport)reportService.generateReportByTree(objectContextTree, documentTemplateNode);
			//response.setContentType(MimeType.getContentType(FileHelper.getFileExtension("doc.PDF")));
			if(report != null){
			response.setContentType("application/pdf");	
			//response.addHeader("Content-Disposition", "attachment; filename=doc.PDF");	
				response.addHeader("Content-Disposition", "inline; filename=doc.PDF");
	        	response.getOutputStream().write(report.getOutputValue().toByteArray(), 0, report.getOutputValue().size());
	        	response.getOutputStream().flush();
	        	response.getOutputStream().close();
			}
		}
		return null;
	}

}
