package net.weg.eng.service;

import net.weg.commons.util.CheckHelper;
import net.weg.commons.xml.JAXBMarshallHelper;
import net.weg.eng.MaestroException;
import net.weg.eng.bean.object.ObjectType;
import net.weg.eng.bean.rule.RuleStatus;
import net.weg.eng.document.service.DocumentManagerService;
import net.weg.eng.document.service.object.ObjectContextDocument;
import net.weg.eng.document.xml.adapter.ObjectContextDocumentAdapter;
import net.weg.eng.document.xml.adapter.SampleDocumentListXmlAdapter;
import net.weg.eng.document.xml.type.SampleDocumentListXmlType;
import net.weg.eng.resource.ObjectContextTreeClientResource;
import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.adapter.ObjectContextRecursiveXmlAdapter;
import net.weg.eng.xml.type.ObjectContextXmlType;
import net.weg.eng.xml.type.document.ObjectContextDocumentXmlType;
import net.weg.maestro.edb.service.VariantStructureService;
import net.weg.maestro.edb.service.VariantStructureUtil;
import net.weg.maestro.tracemonitor.DebuggerMonitor;
import net.weg.maestro.tracemonitor.MessageLogType;
import net.weg.maestro.tracemonitor.TraceLevel;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;


@WebService(name = "EngineeringProvider", targetNamespace = "http://soa.weg.net/eng/maestro/engineeringProvider")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)
public class EngineeringProvider {

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
	private VariantStructureService variantStructureService;

	@Resource
	private ObjectContextTreeClientResource objectContextTreeClientResource;

	@Resource
	private ObjectTypeService objectTypeService;

	@Resource
	private VariantStructureUtil variantStructureUtil;

	/**
	 * Informações de testes para geração de e manipulação de dados de
	 * engenharia
	 * 
	 * @param user
	 * @param objecttypename
	 * @param material
	 * @param documentContext
	 * @return ObjectContextXmlType
	 */
	@WebResult(name = "ObjectContextDocument")
	@WebMethod(operationName = "load")
	public ObjectContextXmlType getDocument(@WebParam(name = "User", header = true) String user, @WebParam(name = "ObjectTypeName") String objecttypename, @WebParam(name = "Material") String material, @WebParam(name = "ObjectContextDocument", partName = "ObjectContextDocument") ObjectContextDocumentXmlType documentContext) {

		try {
		    printRequestToDebuggerMonitor(user, objecttypename, material, documentContext);
			addLanguage(documentContext);
			ObjectContextDocument objectContextDocument = generateInternalDocument(user, objecttypename, material, documentContext);
			objectContextDocument.setChildrenVisible(true);
			ObjectContext data = objectContextDocument.getChildByAlias("DATA");
			if (data != null) {
				this.objectContextTree.setObjectContextRoot(data);
			}
			return objectContextRecursiveXmlAdapter.marshal(this.objectContextTree.getObjectContextRoot().getDecorated(ObjectContextDocument.class), ObjectContextDocumentAdapter.getInstance());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MaestroException(ex); 
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

    private ObjectContextDocument generateInternalDocument(String user, String objectTypeName, String material, ObjectContextDocumentXmlType documentContext) throws Exception {
		return generateInternalDocument(user, objectTypeName, material, null, documentContext, null);
	}

    private ObjectContextDocument generateInternalDocument(String user, String objectTypeName, String material, String productionOrderDate, ObjectContextDocumentXmlType documentContext, ObjectContextXmlType vcContext) throws Exception {
		authenticate(user);
		changeRuleStatus(documentContext);
		objectContextTree.cleanContextRoot();
		ObjectContextDocumentAdapter.getInstance().unmarshal(documentContext, this.objectContextTree.getRoot());
		return this.documentManagerService.getDocument(objectTypeName, material, productionOrderDate, documentContext, vcContext);
	}

	protected void changeRuleStatus(ObjectContextDocumentXmlType documentContext) {
		if (documentContext.getRuleStatus() != null) {
			this.objectContextTree.setRuleStatus(RuleStatus.valueOf(documentContext.getRuleStatus().value()));
		}
	}

	private void authenticate(String user) {
		if (user == null)
			throw new RuntimeException("User not informed!");

		this.managerAuthenticator.authenticate(user);
	}

	@WebResult(name = "DataSheetData")
	@WebMethod(operationName = "getFile")
	public SampleDocumentListXmlType getSampleDocument(@WebParam(name = "User", header = true) String user,
													   @WebParam(name = "ObjectTypeName") String objectTypeName,
													   @WebParam(name = "Material") String material,
													   @WebParam(name = "ProductionOrderDate") String productionOrderDate,
													   @WebParam(name = "ObjectContextDocument", partName = "ObjectContextDocument") ObjectContextDocumentXmlType documentContext,
													   @WebParam(name = "VariantConfigurationContext", partName = "VariantConfigurationContext") ObjectContextXmlType vcContext) {
		try {
            printRequestToDebuggerMonitor(user, objectTypeName, material, productionOrderDate, documentContext, vcContext);
			DebuggerMonitor.getInstance().logString(MessageLogType.Execution, TraceLevel.StringLog, JAXBMarshallHelper.getRequestXml(documentContext));
			if (vcContext != null && productionOrderDate == null) {
				throw new RuntimeException("ProductionOrderDate must be informed using a VariantConfigurationContext");
			}
			addLanguage(documentContext);
			ObjectContextDocument objectContextDocument = generateInternalDocument(user, objectTypeName, material, productionOrderDate, documentContext, vcContext);
			return SampleDocumentListXmlAdapter.getInstance().marshal(objectContextDocument, documentContext);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MaestroException(ex);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	@WebResult(name = "Find")
	@WebMethod(operationName = "findObject")
	public ObjectContextXmlType findObject(@WebParam(name = "User", header = true) String user, @WebParam(name = "Language") String language, @WebParam(name = "HeaderId") Long headerId, @WebParam(name = "ObjectTypeName") String objectTypeName) {

		try {
			authenticate(user);
			ConfigurationManagerHelper.getInstance().setCurrentLanguage(language);
			ObjectType objectType = objectTypeService.findObjectTypeByName(objectTypeName);
			objectContextTree.setDisabledRuleInvoker(true);
			ObjectContextXmlType objectContextXmlType = objectContextTreeClientResource.load(headerId, objectType.getId());
			objectContextTree.setDisabledRuleInvoker(false);
			return objectContextXmlType;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MaestroException(ex);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	@WebResult(name = "FindByMaterial")
	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1", operationName = "findObjectByMaterial")
	public ObjectContextXmlType findObjectByMaterial(@WebParam(name = "User", header = true) String user, @WebParam(name = "Language") String language, @WebParam(name = "Material") String material, @WebParam(name = "ObjectTypeName") String objectTypeName) {

		try {
			authenticate(user);
			ConfigurationManagerHelper.getInstance().setCurrentLanguage(language);
			ObjectType objectType = objectTypeService.findObjectTypeByName(objectTypeName);
			Long rootHeaderId = variantStructureUtil.executeFilterByMaterialAndObjectType(material, objectType.getId());
			objectContextTree.setDisabledRuleInvoker(true);
			ObjectContextXmlType objectContextXmlType = objectContextTreeClientResource.load(rootHeaderId, objectType.getId());
			objectContextTree.setDisabledRuleInvoker(false);
			return objectContextXmlType;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MaestroException(ex);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}
	
	@WebResult(name = "FindByGrouper")
	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1", operationName = "findObjectByGrouper")
	public ObjectContextXmlType findObjectByGrouper(@WebParam(name = "User", header = true) String user, @WebParam(name = "Language") String language, @WebParam(name = "Grouper") String grouper, @WebParam(name = "ObjectTypeName") String objectTypeName) {

		try {
			authenticate(user);
			ConfigurationManagerHelper.getInstance().setCurrentLanguage(language);
			ObjectType objectType = objectTypeService.findObjectTypeByName(objectTypeName);
			Long rootHeaderId = variantStructureUtil.executeFilterByGrouperAndObjectTypesConfigurator(grouper);
			objectContextTree.setDisabledRuleInvoker(true);
			ObjectContextXmlType objectContextXmlType = objectContextTreeClientResource.load(rootHeaderId, objectType.getId());
			objectContextTree.setDisabledRuleInvoker(false);
			return objectContextXmlType;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new MaestroException(ex);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	@WebResult(name = "ObjectContext")
	@WebMethod(operationName = "getDataSheet")
	public ObjectContextXmlType getDataSheet(@WebParam(name = "User", header = true) String user, @WebParam(name = "ObjectTypeName") String objecttypename, @WebParam(name = "Material") String material, @WebParam(name = "ObjectContextDocument", partName = "ObjectContextDocument") ObjectContextDocumentXmlType documentContext) {

		ObjectContext objectContext;
		try {
			authenticate(user);
			addLanguage(documentContext);

			objectContext = variantStructureService.findTechnicalData(objecttypename, material);
			return objectContextRecursiveXmlAdapter.marshal(objectContext);

		} catch (Exception e) {
			e.printStackTrace();
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	private void addLanguage(ObjectContextDocumentXmlType documentContext) {
		if (documentContext != null && !CheckHelper.isListEmpty(documentContext.getLanguages())) {
			ConfigurationManagerHelper.getInstance().setCurrentLanguage(documentContext.getLanguages().get(0).toLowerCase());
		}
	}

    private void printRequestToDebuggerMonitor(String user, String objectTypeName, String material, ObjectContextDocumentXmlType documentContext) {
		printRequestToDebuggerMonitor(user, objectTypeName, material, null, documentContext, null);
	}

    private void printRequestToDebuggerMonitor(String user, String objectTypeName, String material, String productionOrderDate, ObjectContextDocumentXmlType documentContext, ObjectContextXmlType vcContext) {
        DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, user);
        DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, objectTypeName);
        DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, material);
        DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, JAXBMarshallHelper.getRequestXml(documentContext));
        if (productionOrderDate != null) DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, productionOrderDate);
        if (vcContext != null) DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, JAXBMarshallHelper.getRequestXml(vcContext));
    }

}
