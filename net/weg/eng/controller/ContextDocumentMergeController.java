package net.weg.eng.controller;

import net.weg.commons.io.IOUtil;
import net.weg.eng.MaestroException;
import net.weg.eng.document.service.DocumentService;
import net.weg.eng.document.service.ObjectContextDocumentService;
import net.weg.eng.document.service.object.ObjectContextDocument;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.util.PDFManager;
import net.weg.iceberg.io.file.MimeType;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContextDocumentMergeController implements Controller {

    private static final String ATTACHMENT_FILENAME = "attachment; filename=";

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    private static final String PDF = ".pdf";

    @Resource
    private ObjectContextDocumentService objectContextDocumentService;

    @Resource
    private DocumentService documentService;

    @Resource
    private ObjectContextTree objectContextTree;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String documentTemplateIds = request.getParameter("documentHeaderIds");
        if (documentTemplateIds != null) {
            List<ObjectContextDocument> objectContextDocuments = objectContextDocumentService.searchAndPrepareObjectContextDocuments(objectContextTree.getObjectContextRoot());
            List<String> documentPaths = new ArrayList<String>();
            boolean hasDocumentWithError = false;
            for (ObjectContext objectContext : objectContextDocuments) {
                for (String documentId : documentTemplateIds.split(";")) {
                    try {
                        processDocumentExtensions(documentPaths, objectContext, documentId);
                    } catch (MaestroException ex) {
                        response.setContentType("text/html;charset=ISO-8859-8");
                        response.getWriter().println("<h3 style=\"color: red; font-family: sans-serif;\">" + ex.getMessage() + "</h3>");
                        for (StackTraceElement element : ex.getStackTrace()) {
                            response.getWriter().println(element.toString());
                        }
                        response.getWriter().flush();
                        response.getWriter().close();
                        hasDocumentWithError = true;
                    }
                }
            }

            processResponseDocuments(response, documentPaths, hasDocumentWithError);
        }
        return null;
    }

    private void processResponseDocuments(HttpServletResponse response, List<String> documentPaths, boolean hasDocumentWithError) throws IOException {
        if (!hasDocumentWithError) {
            PDDocument doc = PDFManager.getInstance().mergeDocuments(documentPaths, StringUtils.EMPTY);
            ServletOutputStream stream = response.getOutputStream();
            response.setContentType(MimeType.PDF);
            response.addHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + UUID.randomUUID() + PDF);
            if (doc != null) {
                doc.save(stream);
                doc.close();
                stream.flush();
                stream.close();
            }
        }
    }

    private void processDocumentExtensions(List<String> documentPaths, ObjectContext objectContext, String documentId) {
        if (objectContext.getObjectHeader().getId() != null && objectContext.getObjectHeader().getId().equals(Long.valueOf(documentId))) {
            String docName = documentService.getDocumentPath(objectContext);
            docName = processPdfExtension(documentPaths, docName);
            if (!IOUtil.getInstance().isFileExist(docName)) {
                documentPaths.remove(docName);
                objectContextDocumentService.visualizeDocument(objectContext, objectContext.getNodeHash());
                docName = documentService.getDocumentPath(objectContext);
                processPdfExtension(documentPaths, docName);
            }
        }
    }

    private String processPdfExtension(List<String> documentPaths, String docName) {
        if (docName != null) {
            if (!docName.contains(".pdf"))
                docName += ".pdf";
            if (!documentPaths.contains(docName))
                documentPaths.add(docName);
            return docName;
        }
        return null;
    }

}
