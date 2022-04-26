package net.weg.soa.eng.maestro.rule;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.weg.eng.MaestroMessageException;
import net.weg.eng.bean.object.ObjectHeader;
import net.weg.eng.bean.rule.RuleExecutionException;
import net.weg.eng.service.RuleServiceProviderImp;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.util.MessageFormatterUtil;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.type.ObjectContextXmlType;

@WebService(name = "RuleServiceSoap", targetNamespace = "http://soa.weg.net/eng/rule/maestro")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class RuleServiceSoapImp implements RuleServiceSoap {

	private static final Logger LOGGER = LogManager.getLogger(RuleServiceSoapImp.class);

	@Resource
	private RuleServiceProviderImp ruleServiceProviderImp;

	@Resource
	private WebServiceContext webServiceContext;

	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "MaestroRuleResponse", targetNamespace = "http://soa.weg.net/eng/rule/maestro", partName = "MaestroRuleResponse")
	public MaestroRuleResponse execute(@WebParam(name = "MaestroRuleRequest", targetNamespace = "http://soa.weg.net/eng/rule/maestro", partName = "MaestroRuleRequest") MaestroRuleRequest maestroRuleRequest) {
		MaestroRuleResponse response = null;
		try {
			response = ruleServiceProviderImp.execute(maestroRuleRequest);
		} catch (MaestroMessageException e) {
			response = prepareMessageDetailed(maestroRuleRequest, e);
		} catch (RuleExecutionException e) {
			response = prepareMessageDetailed(maestroRuleRequest, e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response = new MaestroRuleResponse();
			response.setObjectContext(new ObjectContextXmlType());
			response.getObjectContext().getError().add(e.getMessage());
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}

		return response;
	}

	private MaestroRuleResponse prepareMessageDetailed(MaestroRuleRequest maestroRuleRequest, Exception e) {
		MaestroRuleResponse response;
		response = new MaestroRuleResponse();
		ObjectContext objectContext = new ObjectContext();
		objectContext.setObjectHeader(new ObjectHeader());
		objectContext.getObjectHeader().setObjectTypeId(maestroRuleRequest.getObjectContext().getObjectHeader().getObjectType().getId());
		objectContext.initializeObjectType();
		String messageDetailed = MessageFormatterUtil.getInstance().getFormatDetailedMessage(objectContext.getRoot().getObjectTypeName(),e.getMessage(),null,false);
		response.setObjectContext(new ObjectContextXmlType());
		response.getObjectContext().getError().add(messageDetailed);
		return response;
	}

}
