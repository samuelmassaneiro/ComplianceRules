package net.weg.eng.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import edu.emory.mathcs.backport.java.util.Collections;
import net.weg.eng.document.bean.Document;
import net.weg.eng.document.service.DocumentService;
import net.weg.eng.maestro.ee.service.DocumentEEService;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.util.PDFManager;
import net.weg.iceberg.io.file.MimeType;

public class DocumentMergeController implements Controller {
	private static final Logger LOGGER = LogManager.getLogger(DocumentMergeController.class);
	private static final String ATTACHMENT_FILENAME = "attachment; filename=";

	private static final String CONTENT_DISPOSITION = "Content-Disposition";

	private static final String PDF = ".pdf";
	
	private static final DocumentCompare DOCUMENT_COMPARE = new DocumentCompare();
  
	@Resource
	private ObjectContextTree objectContextTree;

	@Resource
	private DocumentEEService documentEEService;

	@Resource
	private DocumentService documentService;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String documentTemplateIds = request.getParameter("documentTemplateIds");
		if (documentTemplateIds != null) {
			List<Document> documents = documentEEService.visualizeDocument(objectContextTree.getRoot(), Arrays.asList(documentTemplateIds.split(";")));
			List<String> documentPaths = new ArrayList<String>();
			try {
				Collections.sort(documents, DOCUMENT_COMPARE);
				
			} catch (Exception e) {
				LOGGER.error(this.getClass() + e.getMessage());
			}
			processDocumentExtension(documents, documentPaths);

			PDDocument doc = PDFManager.getInstance().mergeDocuments(documentPaths, StringUtils.EMPTY);
			ServletOutputStream stream = response.getOutputStream();
			response.setContentType(MimeType.PDF);
			response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + UUID.randomUUID() + PDF);
			if (doc != null) {
				doc.save(stream);
				stream.flush();
				stream.close();
				doc.close();
			}
		}
		return null;
	}

	private void processDocumentExtension(List<Document> documents, List<String> documentPaths) {
		for (Document document : documents) {
			String docName = this.documentService.getDocumentPath(document);
			if (docName != null) {
				if (!docName.contains(".pdf"))
					docName += ".pdf";
				documentPaths.add(docName);
			}
		}
	}
}
