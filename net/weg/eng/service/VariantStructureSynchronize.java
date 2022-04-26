package net.weg.eng.service;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.MaestroException;
import net.weg.eng.bean.rule.RuleStatus;
import net.weg.eng.dao.object.ObjectTypeDao;
import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.ObjectContextTree;
import net.weg.eng.service.object.ObjectService;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.adapter.ObjectContextRecursiveXmlAdapter;
import net.weg.eng.xml.type.ObjectContextXmlType;
import net.weg.maestro.edb.service.ObjectServiceEdb;
import net.weg.maestro.edb.service.VariantStructureService;
import net.weg.maestro.edb.service.VariantStructureUtil;
import net.weg.maestro.edb.xml.adapter.MaterialCatalogListXmlAdapter;
import net.weg.maestro.edb.xml.type.MaterialCatalogListXmlType;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(name = "VariantStructureSynchronize", targetNamespace = "http://soa.weg.net/eng/maestro/variantStructureSynchronize")
public class VariantStructureSynchronize {

	private static final String YCATALOGUES = "YCATALOGUES";
	
	@Resource
	private VariantStructureService variantStructureService;
	
	@Resource
	private ObjectContextRecursiveXmlAdapter objectContextRecursiveXmlAdapter;

	@Resource
	private WebServiceContext webServiceContext;

	@Resource
	private ManagerAuthenticator managerAuthenticator;
	
	@Resource
	private ObjectContextTree objectContextTree;

	@Resource
	private ObjectService objectService;
	
	@Resource
	private ObjectServiceEdb objectServiceEdb;
	
	@Resource
	private ObjectTypeDao objectTypeDao;
	
	@WebResult(name = "VariantStructure")
	@WebMethod
	public ObjectContextXmlType createStructure(@WebParam(name = "User", header = true) String user, @WebParam(name = "RuleMode", header = true) RuleStatus status, @WebParam(name = "ObjectTypeName") String objecttypename, @WebParam(name = "Material") String material, @WebParam(name = "DatasheetNumber") String datasheetNumber, @WebParam(name = "DatasheetYear") String datasheetYear, @WebParam(name = "Plant") String plant) {
		return this.createStructureForSpecificCatalogues(user, status,objecttypename, material, datasheetNumber, datasheetYear, null, plant);
	}	
	
	@WebResult(name = "VariantStructure")
	@WebMethod
	public ObjectContextXmlType createStructureForSpecificCatalogues(@WebParam(name = "User", header = true) String user, @WebParam(name = "RuleMode", header = true) RuleStatus status, @WebParam(name = "ObjectTypeName") String objecttypename, @WebParam(name = "Material") String material, @WebParam(name = "DatasheetNumber") String datasheetNumber, @WebParam(name = "DatasheetYear") String datasheetYear, @WebParam(name = "Catalogue") List<String> catalogues,@WebParam(name = "Plant") String plant) {
		ObjectContext objectContextRoot = null;
		try {
			if (user == null)
				throw new RuntimeException("User not informed!");
			this.managerAuthenticator.authenticate(user);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(VariantStructureUtil.MATERIAL, material);
			if(datasheetNumber != null){
				params.put(VariantStructureService.NUMERO_FOLHA_DADOS, datasheetNumber);
				params.put(VariantStructureService.ANO_FOLHA_DADO, datasheetYear);
			}
			if(plant != null) {
				params.put(VariantStructureService.CENTRO_PRODUCAO, plant);
			}

			addCatalogues(catalogues, params);
			addStatus(status);
			objectContextRoot = variantStructureService.createStructureTransactionalReadOnly(objecttypename, params);
			return objectContextRecursiveXmlAdapter.marshal(objectContextRoot);
		} catch (Exception e) {
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}
	
	@WebResult(name = "VariantStructure")
	@WebMethod
	public String createMachineLinkDeltaForElectricalLine(@WebParam(name = "User", header = true) String user, @WebParam(name = "CodLinha") String codLinhaEletrica, @WebParam(name = "Fase") String fase, @WebParam(name = "Frequencia") String frequencia, @WebParam(name = "HeaderIdLinhaEletrica") Long headerIdLinhaEletrica) {
		try {
			if (user == null)
				throw new RuntimeException("User not informed!");
			this.managerAuthenticator.authenticate(user);
			StringBuilder sb = new StringBuilder();
			if (codLinhaEletrica != null && fase!= null && frequencia != null) {
				
				List<String> resultLoad = objectServiceEdb.createLinkMaquinaEletricaMotorDelta(codLinhaEletrica, frequencia, fase, headerIdLinhaEletrica);
				for (String lem : resultLoad) {
					sb.append(lem);
				}
			}
			return sb.toString();
		} catch (Exception e) {
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}
	
	@WebResult(name = "VariantStructure")
	@WebMethod
	public ObjectContextXmlType addMaterial(@WebParam(name = "User", header = true) String user, @WebParam(name = "ObjectType") String objectType, @WebParam(name = "ObjectHeaderId") Long objectHeaderId, @WebParam(name = "MaterialId") String materialId, @WebParam(name = "Catalogue") List<String> catalogues, @WebParam(name = "Plant") String plant) {
		try {
			variantStructureService.addMaterial(objectType, materialId, objectHeaderId);
			return createStructureForSpecificCatalogues(user, RuleStatus.Released, objectType, materialId, null, null, catalogues, plant);
		} catch (Exception e) {
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

	protected void addCatalogues(List<String> catalogues, Map<String, Object> params) {
		if(!CheckHelper.isListEmpty(catalogues)){
			params.put(YCATALOGUES, catalogues);
		}
	}

	protected void addStatus(RuleStatus status) {
		if(status != null){
			objectContextTree.setRuleStatus(status);
		}
	}
	
	@WebMethod
	public ObjectContextXmlType clearConfiguration(@WebParam(name = "User", header = true) String user, @WebParam(name = "ObjectType") String objectType, @WebParam(name = "ObjectHeaderId") Long objectHeaderId) {
		try {
			
			ObjectContext objectContext = this.objectService.find(this.objectTypeDao.findSingleByName(objectType).getId(), objectHeaderId);
			this.objectService.delete(objectContext);
			
			return null;
		} catch (Exception e) {
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}
	
	@WebResult(name = "VariantStructure")
	@WebMethod
	public MaterialCatalogListXmlType findObjectContextChanged(@WebParam(name = "StartDate", header = true) String startDate, @WebParam(name = "EndDate", header = true) String endDate ) {
		try {			
			return MaterialCatalogListXmlAdapter.getInstance().marshal(this.objectServiceEdb.findObjectContextChanged(startDate, endDate));			
		} catch (Exception e) {
			throw new MaestroException(e);
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
	}

}
