package net.weg.eng.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.weg.commons.util.CheckHelper;
import net.weg.eng.MaestroException;
import net.weg.eng.bean.Characteristic;
import net.weg.eng.bean.object.CharacteristicCriteria;
import net.weg.eng.bean.object.CriteriaCondition;
import net.weg.eng.bean.object.ObjectFilter;
import net.weg.eng.bean.object.ObjectType;
import net.weg.eng.bean.object.ObjectValue;
import net.weg.eng.bean.object.ObjectVariant;
import net.weg.eng.bean.rule.OperatorType;
import net.weg.eng.dao.object.MaestroFlyweight;
import net.weg.maestro.plm.service.MaterialService;
import net.weg.eng.service.object.ObjectFilterHelper;
import net.weg.eng.service.object.ObjectFilterResult;
import net.weg.eng.service.object.ObjectFilterService;
import net.weg.soa.eng.maestro.filter.CharacteristicFilter;
import net.weg.soa.eng.maestro.filter.ObjectDataFilter;
import net.weg.soa.eng.maestro.filter.ObjectRequest;
import net.weg.soa.eng.maestro.filter.ObjectResponse;
import net.weg.soa.eng.maestro.filter.ObjectResult;
import net.weg.soa.eng.maestro.filter.ObjectVariantResult;
import net.weg.soa.eng.maestro.filter.PropertyValue;

@Service("filterProvider")
public class FilterProviderImp {

	private static final String OBJECTTYPE_MUST_BE_INFORMED = "object_type_must_be_informed";
	// private static final String OBJECT_NOT_FOUND = "Object not found! ";
	private static final String OBJECT_NOT_FOUND = "object_not_found";
	@Resource
	private ObjectFilterService objectFilterService;
	@Resource
	private MaterialService materialService;
	@Resource
	private ObjectFilterHelper objectFilterHelper;
	public static final int LIMIT = 1000;
	
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}

	public void setObjectFilterService(ObjectFilterService objectFilterService) {
		this.objectFilterService = objectFilterService;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ObjectResponse execute(ObjectRequest requestFilter) {
		ObjectFilter objectFilter = processRequest(requestFilter);
		Map<String, CharacteristicFilter> responseNames = convertMap(requestFilter.getResponseName());
		return processResponse(objectFilter, responseNames);
	}

	private Map<String, CharacteristicFilter> convertMap(List<CharacteristicFilter> list) {
		if (!CheckHelper.isListEmpty(list)) {
			Map<String, CharacteristicFilter> responses = new HashMap<String, CharacteristicFilter>();
			for (CharacteristicFilter responseName : list) {
				responses.put(responseName.getName(), responseName);
			}
			return responses;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ObjectResponse processResponse(ObjectFilter objectFilter, Map<String, CharacteristicFilter> responseNames) {
		if (objectFilter != null) {
			List<ObjectFilterResult> results = objectFilter.getResults();
			if (!CheckHelper.isListEmpty(results)) {
				ObjectResponse responseFilter = new ObjectResponse();
				Map<Long, Long> materialIds = findMaterial(objectFilterHelper.toVariants(results), objectFilter.getObjectTypeId());
				for (ObjectFilterResult objectFilterResult : results) {
					processObjectVariant(responseNames, responseFilter, materialIds, objectFilterResult.getObjectVariant());
				}

				return responseFilter;
			}
		}
		throw new MaestroException(OBJECT_NOT_FOUND);
	}

	private void processObjectVariant(Map<String, CharacteristicFilter> responseNames, ObjectResponse responseFilter, Map<Long, Long> materialIds, ObjectVariant objectVariant) {
		ObjectResult objectResult = new ObjectResult();
		objectResult.setId(objectVariant.getObjectHeader().getId());
		ObjectVariantResult objectVariantResult = processValueResponse(responseNames, objectVariant);
		if (objectVariantResult != null) {
			objectResult.getObjectVariant().add(objectVariantResult);
			setMaterialResult(materialIds, objectVariant, objectVariantResult);
			responseFilter.getObjectHeader().add(objectResult);
		}
	}

	private void setMaterialResult(Map<Long, Long> materialIds, ObjectVariant objectVariant, ObjectVariantResult objectResult) {
		if (materialIds != null) {
			Long materialId = materialIds.get(objectVariant.getId());
			if (materialId != null)
				objectResult.setMaterial(materialId.toString());
		}
	}

	private ObjectVariantResult processValueResponse(Map<String, CharacteristicFilter> responseNames, ObjectVariant objectVariant) {
		if (!CheckHelper.isListEmpty(objectVariant.getObjectValues())) {
			ObjectVariantResult objectVariantResult = new ObjectVariantResult();
			objectVariantResult.setId(objectVariant.getId());
			for (ObjectValue objectValue : objectVariant.getObjectValues()) {
				if (responseNames == null || responseNames.get(objectValue.getCharacteristic().getName()) != null) {
					processCriteria(objectVariantResult, objectValue);
				}
			}
			return objectVariantResult;
		}
		return null;
	}

	private void processCriteria(ObjectVariantResult objectResult, ObjectValue objectValue) {
		CharacteristicFilter criteria = new CharacteristicFilter();
		if (objectValue.getValue() != null) {
			PropertyValue value = new PropertyValue();
			value.setValue(objectValue.getValue().toString());
			value.setDescription(objectValue.getValueDescription());
			criteria.getPropertyValue().add(value);
		}
		criteria.setName(objectValue.getCharacteristic().getName());
		objectResult.getObjectValue().add(criteria);

	}

	// verificar forma de fazer pesquisa com apenas uma query
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<Long, Long> findMaterial(List<ObjectVariant> results, Long objectTypeId) {
		return this.materialService.findMaterialIdByObjectVariantId(results, objectTypeId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public ObjectFilter processRequest(ObjectRequest requestFilter) {
		ObjectFilter objectFilter = new ObjectFilter();
		if (requestFilter.getObjectTypeId()==0)
			throw new MaestroException(OBJECTTYPE_MUST_BE_INFORMED);
		objectFilter.setObjectTypeId(requestFilter.getObjectTypeId());
		//TODO: unificar classes dataFilter
		convertObjectDataFilter(requestFilter, objectFilter);
		if (!CheckHelper.isListEmpty(requestFilter.getCriteria())) {
			Map<String, Characteristic> characteristics = new HashMap<String, Characteristic>();
			if (!CheckHelper.isListEmpty(requestFilter.getResponseName())) {
				createCharacteristicMap(requestFilter.getResponseName(), characteristics, requestFilter.getObjectTypeId());
			}
			createCharacteristicMap(requestFilter.getCriteria(), characteristics, requestFilter.getObjectTypeId());
			requestFilter.getCriteria().addAll(requestFilter.getResponseName());
			processRequestCriterias(requestFilter, objectFilter, characteristics);
		}
		objectFilter.setStart(0);
		objectFilter.setLimit(LIMIT);
		return this.objectFilterService.filter(objectFilter);		
	}

	private void convertObjectDataFilter(ObjectRequest requestFilter, ObjectFilter objectFilter) {
		objectFilter.setObjectDataFilters(new ArrayList<net.weg.eng.bean.object.ObjectDataFilter>());
		for (ObjectDataFilter objectDataFilterXml : requestFilter.getObjectDataFilter()) {
			net.weg.eng.bean.object.ObjectDataFilter objectDataFilter = new net.weg.eng.bean.object.ObjectDataFilter();
			objectDataFilter.setType(objectDataFilterXml.getType());
			objectDataFilter.setValueConditions(objectDataFilterXml.getValueCondition());
			objectFilter.getObjectDataFilters().add(objectDataFilter);
			objectFilter.addCriteria(objectFilterHelper.createCharacteristicCriteria(objectFilter.getObjectTypeId(), objectDataFilterXml.getType(), objectDataFilterXml.getValueCondition()));
		}
	}

	private void processRequestCriterias(ObjectRequest requestFilter, ObjectFilter objectFilter, Map<String, Characteristic> characteristics) {
		if (!CheckHelper.isListEmpty(characteristics))
			for (CharacteristicFilter criteria : requestFilter.getCriteria()) {
				processRequestCriteria(objectFilter, characteristics, criteria);

			}
	}

	private void processRequestCriteria(ObjectFilter objectFilter, Map<String, Characteristic> characteristics, CharacteristicFilter criteria) {
		if (criteria.getName() != null) {
			Characteristic characteristic = characteristics.get(criteria.getName());
			if (characteristic != null) {
				CharacteristicCriteria criteriaFilter = new CharacteristicCriteria();
				criteriaFilter.setObjectTypeNode(criteria.getObjectTypeNode());
				criteriaFilter.setCharacteristic(characteristic);
				processRequestValue(criteria, criteriaFilter);
				criteriaFilter.setObjectFilter(objectFilter);
				objectFilter.addCriteria(criteriaFilter);

				//TODO: Para o filtro por serviço, somente será retornado todas as caracteristicas no ResponseName
				//O contrato do filtro por service deve ser alterado para utilizar o parametro visible para padronizar.
				//A linha abaixo pode ser alterada para true, pois o resultado será limitado pelo ResponseName				
				criteriaFilter.setVisible(true);
			}
		}
	}

	private void processRequestValue(CharacteristicFilter criteria, CharacteristicCriteria criteriaFilter) {
		if (!CheckHelper.isListEmpty(criteria.getPropertyValue())) {
			if (criteria.getOperator() == null)
				criteriaFilter.setOperator(OperatorType.Equality);
			else
				criteriaFilter.setOperator(OperatorType.valueOf(criteria.getOperator().value().toString()));
			for (PropertyValue value : criteria.getPropertyValue()) {
				if (!CheckHelper.isNullOrEmpty(value.getValue())) {
					criteriaFilter.addCriteriaCondition(CriteriaCondition.newInstance(value.getValue(), criteriaFilter));
				}
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<String, Characteristic> createCharacteristicMap(List<CharacteristicFilter> list, Map<String, Characteristic> characteristicMaps, Long objectTypeId) {
		ObjectType objectType = (ObjectType) MaestroFlyweight.getInstance().get(ObjectType.class, objectTypeId);
		if (!CheckHelper.isListEmpty(list)) {
			for (CharacteristicFilter characteristicFilter : list) {
				Characteristic characteristic = objectType.searchCharacteristic(characteristicFilter.getName());
				if (characteristic != null)
					characteristicMaps.put(characteristic.getName(), characteristic);
			}
			return characteristicMaps;
		}
		return null;
	}
}
