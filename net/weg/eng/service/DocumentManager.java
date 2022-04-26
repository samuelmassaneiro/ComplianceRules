package net.weg.eng.service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import net.weg.eng.MaestroException;
import net.weg.eng.document.service.DocumentManagerService;
import net.weg.eng.document.service.object.ObjectContextDocument;
import net.weg.eng.document.xml.adapter.ObjectContextDocumentAdapter;
import net.weg.eng.document.xml.adapter.SampleDocumentListXmlAdapter;
import net.weg.eng.document.xml.type.SampleDocumentListXmlType;
import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.service.object.ObjectService;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.adapter.ObjectContextRecursiveXmlAdapter;
import net.weg.eng.xml.type.ObjectContextXmlType;
import net.weg.eng.xml.type.document.ObjectContextDocumentXmlType;


@WebService(name = "DocumentManager", targetNamespace = "http://soa.weg.net/eng/maestro/documentManager")
public class DocumentManager {

	@Resource
	private ObjectContextRecursiveXmlAdapter objectContextRecursiveXmlAdapter;
	
	@Resource
	private WebServiceContext webServiceContext;
	
	@Resource
	private ManagerAuthenticator managerAuthenticator;
	
	@Resource
	private ObjectContextTree objectContextTree;
	
	@Resource
	private DocumentManagerService documentManagerService;
	

	@Resource
	private ObjectService objectService;
	
	
	@WebResult(name ="ObjectContextDocument")
	@WebMethod(operationName = "generateContext")
	public ObjectContextXmlType getDocument(
			@WebParam(name="User", header=true) String user, 
			@WebParam(name = "ObjectTypeName") String objecttypename, 
			@WebParam(name = "Material") String material, 
			@WebParam(name = "ObjectContextDocument",  partName = "ObjectContextDocument")
			ObjectContextDocumentXmlType documentContext){
		
		try {
			ObjectContextDocument objectContextDocument = generateDocument(user, objecttypename, material, documentContext);
			//ObjectContextDocument objectContextDocument = generate(user, objecttypename, material, documentContext);
			//return  ObjectContextDocumentAdapter.getInstance().marshal(objectContextDocument);
			//return objectContextRecursiveXmlAdapter.marshal(this.objectContextTree.getObjectContextRoot().getDecorated(ObjectContextDocument.class), ObjectContextDocumentAdapter.getInstance());
			return objectContextRecursiveXmlAdapter.marshal(objectContextDocument, ObjectContextDocumentAdapter.getInstance());

		} catch (Exception e) {
			e.printStackTrace();
			throw new MaestroException(e);
		}finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	private ObjectContextDocument generateDocument(String user, String objecttypename, String material, ObjectContextDocumentXmlType documentContext) throws Exception {
		authenticate(user);		
		objectContextTree.cleanContextRoot();
		ObjectContextDocument objectContextDocument = ObjectContextDocumentAdapter.getInstance().unmarshal(documentContext, this.objectContextTree.getRoot());
		objectService.initializeObjectVariant(objectContextDocument.getObjectHeader());	
		this.documentManagerService.getDocument(objecttypename, material, documentContext);
		return objectContextDocument;
	}

	private void authenticate(String user) {
		if(user == null)
			throw new RuntimeException("User not informed!");
		
		this.managerAuthenticator.authenticate(user);
	}
	
	@WebResult(name ="ObjectContextDocument")
	@WebMethod(operationName = "generateSampleContext")
	public SampleDocumentListXmlType getSampleDocument(
			@WebParam(name="User", header=true) String user, 
			@WebParam(name = "ObjectTypeName") String objecttypename, 
			@WebParam(name = "Material") String material, 
			@WebParam(name = "ObjectContextDocument",  partName = "ObjectContextDocument")
			ObjectContextDocumentXmlType documentContext
		){
		
		try {
			generateDocument(user, objecttypename, material, documentContext);			
			return SampleDocumentListXmlAdapter.getInstance().marshal(this.objectContextTree.getRoot().getDecorated(ObjectContextDocument.class));

		} catch (Exception e) {
			e.printStackTrace(); 
			throw new MaestroException(e);
		}finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

}
