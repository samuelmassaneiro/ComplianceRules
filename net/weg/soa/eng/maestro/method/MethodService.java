
package net.weg.soa.eng.maestro.method;

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
@WebServiceClient(name = "MethodService", targetNamespace = "http://soa.weg.net/eng/maestro/method", wsdlLocation = "file:/C:/Projetos/maestro/development/maestro/src/main/resources/MethodService.wsdl")
public class MethodService extends Service {

    private final static URL METHODSERVICE_WSDL_LOCATION;
    private final static Logger LOGGER = LogManager.getLogger(net.weg.soa.eng.maestro.method.MethodService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = net.weg.soa.eng.maestro.method.MethodService.class.getResource(".");
            url = new URL(baseUrl, "file:/C:/Projetos/maestro/development/maestro/src/main/resources/MethodService.wsdl");
        } catch (MalformedURLException e) {
            LOGGER.warn("Failed to create URL for the wsdl Location: 'file:/C:/Projetos/maestro/development/maestro/src/main/resources/MethodService.wsdl', retrying as a local file");
            LOGGER.warn(e.getMessage());
        }
        METHODSERVICE_WSDL_LOCATION = url;
    }

    public MethodService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MethodService() {
        super(METHODSERVICE_WSDL_LOCATION, new QName("http://soa.weg.net/eng/maestro/method", "MethodService"));
    }

    /**
     * 
     * @return
     *     returns MethodServiceSoap
     */
    @WebEndpoint(name = "MethodServiceSoap")
    public MethodServiceSoap getMethodServiceSoap() {
        return super.getPort(new QName("http://soa.weg.net/eng/maestro/method", "MethodServiceSoap"), MethodServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MethodServiceSoap
     */
    @WebEndpoint(name = "MethodServiceSoap")
    public MethodServiceSoap getMethodServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://soa.weg.net/eng/maestro/method", "MethodServiceSoap"), MethodServiceSoap.class, features);
    }

}