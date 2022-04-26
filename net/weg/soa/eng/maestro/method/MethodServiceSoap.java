
package net.weg.soa.eng.maestro.method;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.weg.eng.xml.type.MaestroMethodRequest;
import net.weg.eng.xml.type.MaestroMethodResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "MethodServiceSoap", targetNamespace = "http://soa.weg.net/eng/maestro/method")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MethodServiceSoap {


    /**
     * 
     * @param maestroMethodRequest
     * @return
     *     returns net.weg.soa.eng.maestro.method.MaestroMethodResponse
     */
    @WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MaestroMethodResponse", targetNamespace = "http://soa.weg.net/eng/maestro/method", partName = "MaestroMethodResponse")
    MaestroMethodResponse execute(
        @WebParam(name = "MaestroMethodRequest", targetNamespace = "http://soa.weg.net/eng/maestro/method", partName = "MaestroMethodRequest")
        MaestroMethodRequest maestroMethodRequest);

}