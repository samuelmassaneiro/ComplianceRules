
package net.weg.soa.eng.maestro.rule;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;



/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "RuleServiceSoap", targetNamespace = "http://soa.weg.net/eng/rule/maestro")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RuleServiceSoap {


    /**
     * 
     * @param maestroRuleRequest
     * @return
     *     returns net.weg.soa.eng.rule.maestro.MaestroRuleResponse
     */
    @WebMethod(action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MaestroRuleResponse", targetNamespace = "http://soa.weg.net/eng/rule/maestro", partName = "MaestroRuleResponse")
    MaestroRuleResponse execute(
        @WebParam(name = "MaestroRuleRequest", targetNamespace = "http://soa.weg.net/eng/rule/maestro", partName = "MaestroRuleRequest")
        MaestroRuleRequest maestroRuleRequest);

}
