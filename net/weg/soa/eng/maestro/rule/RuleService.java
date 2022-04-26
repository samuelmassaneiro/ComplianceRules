
package net.weg.soa.eng.maestro.rule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


 
/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "RuleService", targetNamespace = "http://soa.weg.net/eng/rule/maestro", wsdlLocation = "file:/C:/Projetos/maestro/development/maestro-admin/src/main/resources/RuleService.wsdl")
public class RuleService
    extends Service
{

    private final static URL RULESERVICE_WSDL_LOCATION;
    private final static Logger LOGGER = LogManager.getLogger(net.weg.soa.eng.maestro.rule.RuleService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = net.weg.soa.eng.maestro.rule.RuleService.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/Projetos/maestro/development/maestro-admin/src/main/resources/RuleService.wsdl");
        } catch (MalformedURLException e) {
            LOGGER.warn("Failed to create URL for the wsdl Location: 'file:/C:/Projetos/maestro/development/maestro-admin/src/main/resources/RuleService.wsdl', retrying as a local file");
            LOGGER.warn(e.getMessage());
        }
        RULESERVICE_WSDL_LOCATION = url;
    }

    public RuleService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RuleService() {
        super(RULESERVICE_WSDL_LOCATION, new QName("http://soa.weg.net/eng/rule/maestro", "RuleService"));
    }

    /**
     * 
     * @return
     *     returns RuleServiceSoap
     */
    @WebEndpoint(name = "RuleServiceSoap")
    public RuleServiceSoap getRuleServiceSoap() {
        return super.getPort(new QName("http://soa.weg.net/eng/rule/maestro", "RuleServiceSoap"), RuleServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RuleServiceSoap
     */
    @WebEndpoint(name = "RuleServiceSoap")
    public RuleServiceSoap getRuleServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://soa.weg.net/eng/rule/maestro", "RuleServiceSoap"), RuleServiceSoap.class, features);
    }

}
