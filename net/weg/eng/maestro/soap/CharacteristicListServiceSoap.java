package net.weg.eng.maestro.soap;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import net.weg.eng.security.ManagerAuthenticator;
import net.weg.eng.service.CharacteristicListService;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.type.characteristiclistsoap.CharacteristicListRequest;
import net.weg.eng.xml.type.characteristiclistsoap.CharacteristicListResponse;


@SuppressWarnings("PMD") // Remover classe
@Deprecated
@WebService(name = "CharacteristicListService", targetNamespace = "http://soa.weg.net/eng/maestro/characteristiclistservice")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CharacteristicListServiceSoap {

	@Resource
	private CharacteristicListService characteristicListService;

	@Resource
	private WebServiceContext webServiceContext;

	@Resource
	private ManagerAuthenticator managerAuthenticator;

	/**
	 * Este método foi criado com o namespace errado
	 * 
	 * @param maestroCharacteristicRequest
	 * @return
	 */
	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "CharacteristicListResponse")
	public CharacteristicListResponse execute(@WebParam(name = "CharacteristicListRequest", targetNamespace = "http://soa.weg.net/eng/maestro/characteristicList", partName = "CharacteristicListRequest") CharacteristicListRequest maestroCharacteristicRequest) {
		CharacteristicListResponse response = new CharacteristicListResponse();
		try {
			response = characteristicListService.execute(maestroCharacteristicRequest, response);
		} finally {
			this.managerAuthenticator.removeProfile();
			SessionUtil.getInstance().closeSession(webServiceContext);
		}
		return response;
	}

}
