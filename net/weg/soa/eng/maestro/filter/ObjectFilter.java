
package net.weg.soa.eng.maestro.filter;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ObjectFilter", targetNamespace = "http://soa.weg.net/eng/maestro/filter")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ObjectFilter {


    /**
     * 
     * @param request
     * @return
     *     returns net.weg.soa.eng.maestro.filter.ObjectResponse
     */
    @WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "Response", targetNamespace = "http://soa.weg.net/eng/maestro/filter", partName = "Response")
    ObjectResponse search(
        @WebParam(name = "Resquest", targetNamespace = "http://soa.weg.net/eng/maestro/filter", partName = "Request")
        ObjectRequest request);

}