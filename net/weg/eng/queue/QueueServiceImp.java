package net.weg.eng.queue;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import net.weg.soa.serviceclient.document.builder.BuildDocumentDWResponse.BuildDocumentDWResult;
import net.weg.soa.serviceclient.document.builder.DocumentBuilderServiceSoap;
import net.weg.soa.serviceclient.document.builder.UploadDocumentDW.DocumentBuilderRequest;
import net.weg.soa.serviceclient.document.builder.UploadDocumentDWResponse.UploadDocumentDWResult;

@WebService(name = "DocumentBuilderServiceSoap", targetNamespace = "http://soa.weg.net/eng/documentbuilder")
@XmlSeeAlso({ net.weg.soa.serviceclient.document.builder.ObjectFactory.class })
public class QueueServiceImp implements DocumentBuilderServiceSoap {

	/**
	 * 
	 * @param documentBuilderRequest
	 * @return returns net.weg.soa.eng.documentbuilder.BuildDocumentResponse.
	 *         BuildDocumentResult
	 */
	@WebMethod(operationName = "BuildDocument", action = "http://soa.weg.net/eng/documentbuilder/BuildDocument")
	@WebResult(name = "BuildDocumentResult", targetNamespace = "http://soa.weg.net/eng/documentbuilder")
	@RequestWrapper(localName = "BuildDocument", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.BuildDocument")
	@ResponseWrapper(localName = "BuildDocumentResponse", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.BuildDocumentResponse")
	public net.weg.soa.serviceclient.document.builder.BuildDocumentResponse.BuildDocumentResult buildDocument(@WebParam(name = "documentBuilderRequest", targetNamespace = "http://soa.weg.net/eng/documentbuilder") net.weg.soa.serviceclient.document.builder.BuildDocument.DocumentBuilderRequest documentBuilderRequest) {
		// queueService.add(documentBuilderRequest);

		return null;
	}

	/**
	 * 
	 * @param documentNumberPartType
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "DeleteCache", action = "http://soa.weg.net/eng/documentbuilder/DeleteCache")
	@WebResult(name = "DeleteCacheResult", targetNamespace = "http://soa.weg.net/eng/documentbuilder")
	@RequestWrapper(localName = "DeleteCache", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.DeleteCache")
	@ResponseWrapper(localName = "DeleteCacheResponse", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.DeleteCacheResponse")
	public String deleteCache(@WebParam(name = "DocumentNumberPartType", targetNamespace = "http://soa.weg.net/eng/documentbuilder") String documentNumberPartType) {
		return null;
	}

	/**
	 * 
	 * @param docBuilderRequestNumber
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "RebuildRequest", action = "http://soa.weg.net/eng/documentbuilder/RebuildRequest")
	@WebResult(name = "RebuildRequestResult", targetNamespace = "http://soa.weg.net/eng/documentbuilder")
	@RequestWrapper(localName = "RebuildRequest", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.RebuildRequest")
	@ResponseWrapper(localName = "RebuildRequestResponse", targetNamespace = "http://soa.weg.net/eng/documentbuilder", className = "net.weg.soa.eng.documentbuilder.RebuildRequestResponse")
	public String rebuildRequest(@WebParam(name = "DocBuilderRequestNumber", targetNamespace = "http://soa.weg.net/eng/documentbuilder") String docBuilderRequestNumber) {
		return null;
	}

	@Override
	public UploadDocumentDWResult uploadDocumentDW(DocumentBuilderRequest documentBuilderRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuildDocumentDWResult buildDocumentDW(net.weg.soa.serviceclient.document.builder.BuildDocumentDW.DocumentBuilderRequest documentBuilderRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
