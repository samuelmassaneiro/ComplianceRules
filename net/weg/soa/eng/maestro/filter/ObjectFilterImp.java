package net.weg.soa.eng.maestro.filter;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.weg.eng.service.FilterProviderImp;

@WebService(name = "ObjectFilterSoap", targetNamespace = "http://soa.weg.net/eng/maestro/filter")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class ObjectFilterImp implements ObjectFilter {

	@Resource
	private FilterProviderImp filterProviderService;

	public void setFilterProviderService(FilterProviderImp filterProviderService) {
		this.filterProviderService = filterProviderService;
	}

	@WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
	@WebResult(name = "Response", targetNamespace = "http://soa.weg.net/eng/maestro/filter", partName = "Response")
	public ObjectResponse search(@WebParam(name = "Resquest", targetNamespace = "http://soa.weg.net/eng/maestro/filter", partName = "Request") ObjectRequest request) {
		return filterProviderService.execute(request);
	}

}
