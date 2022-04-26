package net.weg.eng.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import net.weg.commons.io.file.FileHelper;
import net.weg.commons.util.CheckHelper;
import net.weg.eng.document.service.DocumentService;
import net.weg.iceberg.io.file.MimeType;

public class DownloadDocumentController implements Controller {

	private static final String DRIVE_WORKS_DOCUMENT_BUILDER = "DriveWorksDocumentBuilder";
	private static final String SOLID_WORKS_DOCUMENT_BUILDER = "SolidWorksDocumentBuilder";
	private static final String ZIP = ".zip";
	private static final String IMPLEMENTER = "implementer";
	private static final String DOCUMENT_NUMBER = "documentNumber";
	private static final String DOCUMENT_PATH = "documentPath";
	private static final String EXTENSION = "extension";
	private static final String FILE = "file";
	private static final String UNDEFINED = "undefined";
	private static final String NULL = "null";
	private static final String CONTENT_DISPOSITION = "contentDisposition";
	private Map<String, String> documentOutputPaths;
	private Map<String, Map<String, String>> documentBuilderPaths;
	@Resource
	private DocumentService documentService;

	public void setDocumentOutputPaths(Map<String, String> documentOutputPaths) {
		this.documentOutputPaths = documentOutputPaths;
	}

	public Map<String, Map<String, String>> getDocumentBuilderPaths() {
		return documentBuilderPaths;
	}

	public void setDocumentBuilderPaths(Map<String, Map<String, String>> documentBuilderPaths) {
		this.documentBuilderPaths = documentBuilderPaths;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String file = request.getParameter(FILE);
		String extension = request.getParameter(EXTENSION);
		String path = request.getParameter(DOCUMENT_PATH);
		String dir = request.getParameter(DOCUMENT_NUMBER);
		String implementer = request.getParameter(IMPLEMENTER);
		String contentDisposition = request.getParameter(CONTENT_DISPOSITION);
		if (ZIP.equals(extension)) {
			if(SOLID_WORKS_DOCUMENT_BUILDER.equals(implementer) || DRIVE_WORKS_DOCUMENT_BUILDER.equals(implementer)) {
				//zip já é gerado pelo DocumentBuilder
				getFile(response, file, path, dir, implementer, contentDisposition);
			} else {
				fileList = new ArrayList<String>();
				//TODO: CRIAR map pelo Spring.
				path = getDocumentPath(dir, path, implementer);
				generateFileList(new File(path), path);
				ByteArrayOutputStream baos = zipIt(path);
				baos.close();
				defineResponse(response, baos);	
			}			
		} else {
			getFile(response, file, path, dir, implementer, contentDisposition);
		}
		return null;
	}

	private void getFile(HttpServletResponse response, String file, String path, String dir, String implementer, String contentDisposition)
			throws FileNotFoundException, IOException {
		FileInputStream input = new FileInputStream(getDocumentPath(dir, path, implementer) + file);
		response.setContentType(MimeType.getContentType(FileHelper.getFileExtension(file)));
		if(contentDisposition == null || contentDisposition.isEmpty() || contentDisposition.trim().isEmpty())
			response.addHeader("Content-Disposition", "attachment; filename=" + file);
		else
			response.addHeader("Content-Disposition", contentDisposition + "; filename=" + file);
		PrintWriter pw = response.getWriter();
		int c = input.read();
		while (c != -1) {
			pw.print((char) c);
			c = input.read();
		}
		input.close();
		pw.flush();
	}
	private void defineResponse(HttpServletResponse response, ByteArrayOutputStream baos) throws IOException {
		response.setContentType("application/zip");
		response.addHeader("Content-Length", String.valueOf(baos.toByteArray().length));
		response.addHeader("Content-Disposition", "attachment; filename=doc.zip");
		response.getOutputStream().write (baos.toByteArray(), 0, baos.toByteArray().length);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	private String getDocumentPath(String dir, String path, String implementer) {
		if (implementer == null)
			implementer = SOLID_WORKS_DOCUMENT_BUILDER;
		
		if(DRIVE_WORKS_DOCUMENT_BUILDER.equals(implementer)) {
			return  Paths.get(documentBuilderPaths.get(implementer).get(documentService.getKeyPath()) + path).toString() + File.separator;
		} else {
			if (CheckHelper.isNullOrEmpty(dir) || NULL.equals(dir) || UNDEFINED.equals(dir))
				if(path==null || UNDEFINED.equals(path)) { 
					return documentBuilderPaths.get(implementer).get(documentService.getKeyPath());
				} else {
					return documentBuilderPaths.get(implementer).get(documentService.getKeyPath()) + path;
				}
			else
				return documentOutputPaths.get(documentService.getKeyPath());
	
		}
		
		
	}
	
	// TODO - CRIAR uTILS PRA ISSO
	@SuppressWarnings("PMD")
	public ByteArrayOutputStream zipIt(String sourceDirectory) {
		byte[] buffer = new byte[10024];
		String source = "";
		ZipOutputStream zos = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			try {
				source = sourceDirectory.substring(sourceDirectory.lastIndexOf("\\") + 1, sourceDirectory.length());
			} catch (Exception e) {
				source = sourceDirectory;
			}
			zos = new ZipOutputStream(baos);
			FileInputStream in = null;
			for (String file : this.fileList) {				
				ZipEntry ze = new ZipEntry(source + file);
				zos.putNextEntry(ze);
				try {
					in = new FileInputStream(sourceDirectory + file);
					int len;
					while ((len = in.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
				} catch(Exception e) {
					System.out.println(e);
				} finally {
					in.close();
				}
				zos.closeEntry();
			}
			zos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return baos;
	}

	private List<String> fileList;

	private void generateFileList(File node, String source) {
		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.toString(), source));
		}
		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename), source);
			}
		}
	}

	private String generateZipEntry(String file, String source) {
		return file.substring(source.length(), file.length());
	}

}
