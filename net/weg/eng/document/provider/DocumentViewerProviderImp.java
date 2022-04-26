package net.weg.eng.document.provider;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.dao.object.ObjectHeaderDao;
import net.weg.eng.document.bean.Document;
import net.weg.eng.document.bean.DocumentStatusType;
import net.weg.eng.document.service.DocumentService;
import net.weg.eng.service.ObjectContextDocumentHelper;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.ObjectService;
import net.weg.eng.util.SessionUtil;
import net.weg.soa.eng.maestro.documentviewer.DocumentViewerRequest;
import net.weg.soa.eng.maestro.documentviewer.DocumentViewerResponse;
import net.weg.soa.eng.maestro.documentviewer.DocumentViewerResponse.DocumentViewers;
import net.weg.soa.eng.maestro.documentviewer.DocumentViewerSoap;
import net.weg.soa.eng.maestro.documentviewer.RequestDocumentViewerType;

@WebService(name = "DocumentViewerSoap", targetNamespace = "http://soa.weg.net/eng/maestro/documentViewer")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class DocumentViewerProviderImp implements DocumentViewerSoap {

	private static final int _350000 = 350000;
	@Resource
	private DocumentService documentService;
	@Resource
	private ObjectHeaderDao objectHeaderDao;
	@Resource
	private ObjectService objectService;

	@Resource
	private WebServiceContext webServiceContext;

	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "DocumentViewerResponse", targetNamespace = "http://soa.weg.net/eng/maestro/documentViewer", partName = "documentViewerResponse")
	public DocumentViewerResponse updateDocumentViewerCache(@WebParam(name = "DocumentViewerRequest", targetNamespace = "http://soa.weg.net/eng/maestro/documentViewer", partName = "documentViewerRequest") DocumentViewerRequest request) {
		DocumentViewerResponse response = new DocumentViewerResponse();
		try {
			boolean updateSucess = true;
			if(!CheckHelper.isListEmpty(request.getDocumentViewers().getDocumentViewer()) && request.getDocumentViewers().getDocumentViewer().get(0).getProcessId() !=null) {
				//TODO: Isso e temporario !!!, todos os processIds (Documentos) serao migrados para estrutura de ObjectHeaders assim que o Celso der ok, e a aplicacao esttiver pelo menos 1 mes em producao
				if(request.getDocumentViewers().getDocumentViewer().get(0).getProcessId().longValue() > _350000) {					
					ObjectContext oc = new ObjectContext();
					objectService.findObjectHeader(request.getDocumentViewers().getDocumentViewer().get(0).getProcessId().longValue(), oc);
					oc.getObjectHeader().getObjectVariants().get(0).searchObjectValueHashByCharacteristicName(ObjectContextDocumentHelper.DOCUMENT_STATUS).get(0).setValue(DocumentStatusType.valueOf(DocumentStatusType.class, request.getDocumentViewers().getDocumentViewer().get(0).getStatus()).getValue());
					if(request.getDocumentViewers().getDocumentViewer().get(0).getError()!= null) {
						oc.getObjectHeader().getObjectVariants().get(0).setNote(request.getDocumentViewers().getDocumentViewer().get(0).getError());
					}
					objectHeaderDao.save(oc.getObjectHeader());
				} else {
					List<Document> documents = prepareRequest(request);
					updateSucess = documentService.updateDocumentViewer(documents);
				}
			}
			
			
			response.setDocumentViewers(new DocumentViewers());
			response.getDocumentViewers().setStatus(updateSucess ? "Update Sucessful" : "UpdateError");
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
		return response;
	}

	private List<Document> prepareRequest(DocumentViewerRequest request) {
		List<Document> documents = new LinkedList<Document>();
		for (RequestDocumentViewerType documentViewersSequence : request.getDocumentViewers().getDocumentViewer()) {
			Document dv = new Document();
			dv.setId(documentViewersSequence.getProcessId().longValue());
			dv.setStatus(DocumentStatusType.valueOf(DocumentStatusType.class, documentViewersSequence.getStatus()));
			if (dv.getStatus().equals(DocumentStatusType.Error)) {
				dv.setNote(documentViewersSequence.getError());
			}
			documents.add(dv);
		}
		return documents;
	}
	
}
