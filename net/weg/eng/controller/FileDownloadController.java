package net.weg.eng.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.document.service.ObjectContextDocumentSipService;
import net.weg.eng.document.service.object.ObjectContextDocument;
import net.weg.eng.service.object.CommandManager;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.service.object.component.ComponentResourceCommand;
import net.weg.eng.xml.type.document.File;

public class FileDownloadController implements Controller { 

	private static final String DOT = ".";
	private static final String INLINE_FILENAME = "inline; filename=";
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONTENT_DISPOSITION = "Content-Disposition";
	private static final String APPLICATION = "application/";

	@Resource
	private ObjectContextTree objectContextTree;

	@Resource
	private ObjectContextDocumentSipService objectContextDocumentSipService;

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String documentTemplateNode = request.getParameter("documentTemplateNode");
		String singleDocumentByExtension = request.getParameter("documentExtension");
		objectContextDocumentSipService.processBeforeDocumentDownload(objectContextTree.getRoot(), singleDocumentByExtension);
		if (!CheckHelper.isNullOrEmpty(documentTemplateNode) && CheckHelper.isNullOrEmpty(singleDocumentByExtension)) {
			processDocumentsInZip(response, documentTemplateNode);
		} else if(!CheckHelper.isNullOrEmpty(documentTemplateNode) && !CheckHelper.isNullOrEmpty(singleDocumentByExtension)) {
			processSingleDocumentByExtension(response, documentTemplateNode, singleDocumentByExtension);
		}
		return null;
	}

	private void processSingleDocumentByExtension(HttpServletResponse response, String documentTemplateNode, String singleDocumentByExtension) throws IOException {
		ObjectContextDocument contextDocument = objectContextTree.getRoot().getDecorated(ObjectContextDocument.class);
		contextDocument.setFiles(new ArrayList<File>());
		contextDocument.getFilesConfig().setMainFileName(documentTemplateNode);
		CommandManager.getInstance().execute(ComponentResourceCommand.class, contextDocument);
		List<String> documentsAdd = new ArrayList<String>();
		File selectedFile = null;
		for (File file : contextDocument.getFiles()) {
			if(!documentsAdd.contains(file.getName()) && !CheckHelper.isNullOrEmpty(file.getName()) && file.getName().toUpperCase().contains((DOT + singleDocumentByExtension).toUpperCase())) {
				selectedFile = file;
				break;
			}
		}
		response.setContentType(APPLICATION + singleDocumentByExtension);
		response.addHeader(CONTENT_LENGTH, String.valueOf(selectedFile.getContent().length));
		response.addHeader(CONTENT_DISPOSITION, INLINE_FILENAME + documentTemplateNode + "." + singleDocumentByExtension);
		response.getOutputStream().write(selectedFile.getContent(), 0, selectedFile.getContent().length);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private void processDocumentsInZip(HttpServletResponse response, String documentTemplateNode) throws IOException {
		ObjectContextDocument contextDocument = objectContextTree.getRoot().getDecorated(ObjectContextDocument.class);
		contextDocument.setFiles(new ArrayList<File>());
		contextDocument.getFilesConfig().setMainFileName(documentTemplateNode);
		CommandManager.getInstance().execute(ComponentResourceCommand.class, contextDocument);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		List<String> documentsAdd = new ArrayList<String>();
		for (File file : contextDocument.getFiles()) {
			if(!documentsAdd.contains(file.getName())) {
				documentsAdd.add(file.getName());
				ZipEntry entry = new ZipEntry(file.getName());
				zos.putNextEntry(entry);
				zos.write(file.getContent());
			}
		}
		zos.close();
		defineResponse(response, baos);
	}

	private void defineResponse(HttpServletResponse response, ByteArrayOutputStream baos) throws IOException {
		response.setContentType("application/zip");
		response.addHeader("Content-Length", String.valueOf(baos.toByteArray().length));
		response.addHeader("Content-Disposition", "inline; filename=doc.zip");
		response.getOutputStream().write(baos.toByteArray(), 0, baos.toByteArray().length);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
