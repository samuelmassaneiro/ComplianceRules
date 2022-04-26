package net.weg.soa.eng.maestro.method;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.weg.eng.service.MethodServiceProviderImp;
import net.weg.eng.util.SessionUtil;
import net.weg.eng.xml.type.MaestroMethodRequest;
import net.weg.eng.xml.type.MaestroMethodResponse;

@WebService(name = "MethodServiceSoap", targetNamespace = "http://soa.weg.net/eng/maestro/method")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class MethodServiceSoapImp implements MethodServiceSoap {

	private static final Logger LOGGER = LogManager.getLogger(MethodServiceSoapImp.class);

	@Resource
	private WebServiceContext webServiceContext;

	@Resource
	private MethodServiceProviderImp methodServiceProviderImp;

	@Override
	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "MaestroMethodResponse", targetNamespace = "http://soa.weg.net/eng/maestro/method", partName = "MaestroMethodResponse")
	public MaestroMethodResponse execute(@WebParam(name = "MaestroMethodRequest", targetNamespace = "http://soa.weg.net/eng/maestro/method", partName = "MaestroMethodRequest") MaestroMethodRequest maestroMethodRequest) {
		MaestroMethodResponse response = null;
		try {
			response = methodServiceProviderImp.execute(maestroMethodRequest);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			response = new MaestroMethodResponse();
			response.getResult().add(e.getMessage());
		} finally {
			SessionUtil.getInstance().closeSession(webServiceContext);
		}

		return response;
	}

}
