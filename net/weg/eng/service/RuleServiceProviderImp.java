package net.weg.eng.service;

import net.weg.commons.util.CheckHelper;
import net.weg.commons.xml.JAXBMarshallHelper;
import net.weg.eng.MaestroException;
import net.weg.eng.bean.object.ObjectType;
import net.weg.eng.bean.object.ObjectValue;
import net.weg.eng.bean.rule.EventContext;
import net.weg.eng.dao.object.MaestroFlyweight;
import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.object.*;
import net.weg.eng.service.object.importer.ObjectImportCommand;
import net.weg.eng.util.MaestroApplicationContext;
import net.weg.eng.util.TreeHashBuilder;
import net.weg.eng.xml.adapter.EventTypeListXmlAdapter;
import net.weg.eng.xml.adapter.ObjectContextRecursiveXmlAdapter;
import net.weg.eng.xml.type.ObjectContextXmlType;
import net.weg.maestro.tracemonitor.DebuggerMonitor;
import net.weg.maestro.tracemonitor.MessageLogType;
import net.weg.maestro.tracemonitor.TraceLevel;
import net.weg.soa.eng.maestro.rule.MaestroRuleRequest;
import net.weg.soa.eng.maestro.rule.MaestroRuleResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/*
 * Retornar os valores opcionais para PTD
 * Adicionar parametro
 */
@Service("ruleServiceProvider")
public class RuleServiceProviderImp extends ServiceBase {
	private static final String MINUS = "-";

	private static final Logger LOGGER = LogManager.getLogger(RuleServiceProviderImp.class);

	private static final String DECODE_CHARACTERISTICS = "DecodeCharacteristics";

	private static final String IMPORT = "Import";

	private static final String USER_IS_NULL_IN_REQUEST = "Usuário vazio na requisição!";
	public static final String MATERIAL = "MATERIAL";
	public static final long VALIDATOR = 3292l;

	@Resource
	private ObjectService objectService;

	@Resource
	private ManagerAuthenticator managerAuthenticator;

	@Resource
	private ObjectContextRecursiveXmlAdapter objectContextRecursiveXmlAdapter;

	@Resource
	private ObjectContextSynchronizerService objectContextSynchronizerService;

	@Resource
	private EventService eventService;

	@Resource
	private EventTypeListXmlAdapter eventTypeListXmlAdapter;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public MaestroRuleResponse execute(MaestroRuleRequest maestroRuleRequest) throws Exception {
		MaestroRuleResponse maestroRuleResponse = null;
		try {
			this.validateUser(maestroRuleRequest);
			DebuggerMonitor.getInstance().logObject(MessageLogType.Request, TraceLevel.ObjectLog, maestroRuleRequest, "RuleService");
			if(DebuggerMonitor.getInstance().checkActiveTraceLevelByUser(TraceLevel.StringLog, maestroRuleRequest.getUser())) {
				DebuggerMonitor.getInstance().logString(MessageLogType.Request, TraceLevel.StringLog, JAXBMarshallHelper.getRequestXml(maestroRuleRequest));
			}
			if(maestroRuleRequest.getLanguage() != null)
				ConfigurationManagerHelper.getInstance().setCurrentLanguage(maestroRuleRequest.getLanguage().toLowerCase());
			ObjectContext objectContextInRequest = initializeObjectContextAndValues(objectContextRecursiveXmlAdapter.unmarshal(maestroRuleRequest.getObjectContext()));
			ObjectContext objectContext = objectService.newObjectContext(maestroRuleRequest.getObjectContext().getObjectHeader().getObjectType().getId());
			TreeHashBuilder.buildAllTree(objectContext);
			transferRequestValuesRecursive(objectContext, objectContextInRequest);
			objectContext.getObjectContextTree().setEvents(objectContext, eventTypeListXmlAdapter.unmarshal(maestroRuleRequest.getObjectContext().getEvent()));
			for(ObjectContext context : objectContext.allContexts())
				context.setRuleStatus(objectContextInRequest.getRuleStatus());
			objectContext = invoke(objectContext);
			objectContext.getObjectHeader().addAllChangedValues(objectContext.getObjectHeader().getObjectVariant().getObjectValues());
			objectContextSynchronizerService.processRuleActions(objectContext);
			ObjectContextXmlType objectContextXmlType = objectContextRecursiveXmlAdapter.marshal(objectContext);
			maestroRuleResponse = new MaestroRuleResponse();
			maestroRuleResponse.setObjectContext(objectContextXmlType);
			this.invalidateObjectHeader(objectContext);
			DebuggerMonitor.getInstance().logObject(MessageLogType.Response, TraceLevel.ObjectLog, maestroRuleResponse, "RuleService");
			if(DebuggerMonitor.getInstance().checkActiveTraceLevelByUser(TraceLevel.StringLog,  maestroRuleRequest.getUser())) {
				DebuggerMonitor.getInstance().logString(MessageLogType.Response, TraceLevel.StringLog, JAXBMarshallHelper.getRequestXml(maestroRuleResponse));
			}
			return maestroRuleResponse;
		} finally {
			if (MonitorManager.getInstance().isMonitoring(MonitorType.Log4j)) {
				String requestXml = JAXBMarshallHelper.getRequestXml(maestroRuleRequest);
				LOGGER.warn(this.getClass() + requestXml);
			}
			this.managerAuthenticator.removeProfile();
		}
	}

	private ObjectContext initializeObjectContextAndValues(ObjectContext objectContext) {
		objectContext.getObjectHeader().getObjectVariant().setObjectHeader(objectContext.getObjectHeader());
		objectService.initializeObjectVariantInTree(objectContext.getObjectHeader());
		objectContext.getObjectContextTree().setObjectContextRoot(objectContext);

		return objectContext;
	}

	private void transferRequestValuesRecursive(ObjectContext objectContextActual, ObjectContext objectContextInRequest) {
		transferRequestValues(objectContextActual, objectContextInRequest);
		if (!CheckHelper.isListEmpty(objectContextInRequest.getChildren())) {
			for (int i = 0; i < objectContextInRequest.getChildren().size(); i++) {
				ObjectContext nextObjectContextInRequest = objectContextInRequest.getChildren().get(i);
				ObjectContext nextObjectContextActual = objectContextActual.getChildByAlias(nextObjectContextInRequest.getAlias());
				// In there is not object context in structure try to create a new instance
				if (nextObjectContextActual == null && nextObjectContextInRequest.getObjectTypeId() != null) {
					String objectTypeName = MaestroApplicationContext.getInstance().getBean(MaestroFlyweight.class).get(ObjectType.class, nextObjectContextInRequest.getObjectTypeId()).getName();
					nextObjectContextActual = objectContextActual.getDecorated(ObjectContextDecorator.class).create(objectTypeName, nextObjectContextInRequest.getAlias());
				}
				transferRequestValuesRecursive(nextObjectContextActual, nextObjectContextInRequest);
			}
		}
	}

	private void transferRequestValues(ObjectContext objectContextActual, ObjectContext objectContextInRequest) {
		if(objectContextInRequest.getObjectHeader().getObjectVariant().getObjectValues() != null) {
			for (ObjectValue objectValueInRequest : objectContextInRequest.getObjectHeader().getObjectVariant().getObjectValues()) {
				ObjectValue objectValueActual= objectContextActual.getObjectHeader().getObjectVariant().searchObjectValueHashByCharacteristicName(objectValueInRequest.getCharacteristic().getName(), objectValueInRequest.getSequence());
				try {
					objectContextActual.getObjectHeader().addChangedValue(objectValueActual);
					objectValueActual.setValueForced(true);
					objectValueActual.setValueClassification(objectValueInRequest.getValueClassification());
					if(objectContextInRequest.getObjectHeader().getObjectType().getId() == VALIDATOR && MATERIAL.equals(objectValueInRequest.getCharacteristic().getName())){
						Long material = Long.parseLong(objectValueInRequest.getValueString());
						objectValueActual.setValue(material.toString(), objectValueInRequest.getValueClassification());
					}else{
						objectValueActual.setValue(objectValueInRequest.getValueString(), objectValueInRequest.getValueClassification());
					}


				} catch(NumberFormatException e){
					processNumberFormatException(objectValueActual, objectValueInRequest);
				}
			}
		}
	}

	protected void processNumberFormatException(ObjectValue objectValueActual, ObjectValue objectValueInRequest) {
		String stringValue = objectValueInRequest.getValueString();
		if(stringValue != null && stringValue.substring(stringValue.length()-1).equals(MINUS)){
			String value = StringUtils.replace(objectValueInRequest.getValueString(), MINUS, StringUtils.EMPTY);
			objectValueActual.setValue(MINUS + value);
		}
	}

	private void invalidateObjectHeader(ObjectContext objectContext) {
		if (objectContext != null) {
			objectContext.setObjectHeader(null);
		}
	}

	private ObjectContext invoke(ObjectContext objectContext) {
		executeByEvents(objectContext);
		return objectContext;
	}

	private void executeByEvents(ObjectContext objectContext) {
		if (!CheckHelper.isListEmpty(objectContext.getEvents())) {
			List<EventContext> events = objectContext.getEvents();
			for (EventContext event : events) {
				if ((CheckHelper.isListEmpty(event.getExecuteRuleGroupIds()) ||
						CheckHelper.isListEmpty(event.getExecuteRuleIds()))
						&& event.getEventType() != null &&
						event.getEventType().getName().equals(DECODE_CHARACTERISTICS) &&
						(objectContext.getObjectHeader().getObjectTypeId().equals(Long.valueOf(1)) ||
								objectContext.getObjectHeader().getObjectTypeId().equals(Long.valueOf(3))||
								objectContext.getObjectHeader().getObjectTypeId().equals(Long.valueOf(44)))) {
					executeImport(objectContext);
					break;
				}
			}
			applyChanges(objectContext, events);
		}
	}

	private void applyChanges(ObjectContext objectContext, List<EventContext> events) {
		objectContext.setEvents(events);
		objectService.applyChanges(objectContext);
	}

	private void executeImport(ObjectContext objectContext) {
		objectContext.setEvents(new ArrayList<>());
		objectContext.addEvent(eventService.findByName(IMPORT), null);
		CommandManager.getInstance().execute(ObjectImportCommand.class, objectContext);
		objectContext.setEvents(null);
	}

	private void validateUser(MaestroRuleRequest maestroRuleRequest) {
		String user = maestroRuleRequest.getUser();
		if (user == null)
			throw new MaestroException(USER_IS_NULL_IN_REQUEST);
		this.managerAuthenticator.authenticate(user);
	}

}
