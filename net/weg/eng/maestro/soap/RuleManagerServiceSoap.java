package net.weg.eng.maestro.soap;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.RuleManagerService;
import net.weg.eng.xml.type.rulemanagersoap.RuleManagerRequest;

@WebService(name = "RuleManagerService", targetNamespace = "http://soa.weg.net/eng/maestro/rulemanagerservice")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class RuleManagerServiceSoap{

	
	
	@Resource
	private RuleManagerService ruleManagerProvider;
	
	@Resource
	private ManagerAuthenticator managerAuthenticator;
	
	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "RuleManagerResponse")
	public boolean saveRules(@WebParam(name = "Username", header = true) String user, @WebParam(name = "RuleManagerRequest", targetNamespace = "http://soa.weg.net/eng/maestro/characteristicList", partName = "RuleManagerRequest") RuleManagerRequest request) {
		validadeUser(user);
		this.managerAuthenticator.authenticate(user);
		ruleManagerProvider.execute(request);
		return true;
	}
	
	private void validadeUser(String user) {
		if (user == null || user.isEmpty())
			throw new RuntimeException("User not informed!");
	}
	
}
