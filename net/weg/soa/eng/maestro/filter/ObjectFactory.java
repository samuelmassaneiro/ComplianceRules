
package net.weg.soa.eng.maestro.filter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.weg.soa.eng.maestro.filter package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName RESQUEST_QNAME = new QName("http://soa.weg.net/eng/maestro/filter", "Resquest");
    private final static QName RESPONSE_QNAME = new QName("http://soa.weg.net/eng/maestro/filter", "Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.weg.soa.eng.maestro.filter
     * 
     */
    public ObjectFactory() {
        super();
    }

    /**
     * Create an instance of {@link ObjectRequest }
     * 
     */
    public ObjectRequest createObjectRequest() {
        return new ObjectRequest();
    }

    /**
     * Create an instance of {@link ObjectResponse }
     * 
     */
    public ObjectResponse createObjectResponse() {
        return new ObjectResponse();
    }

    /**
     * Create an instance of {@link PropertyValue }
     * 
     */
    public PropertyValue createPropertyValue() {
        return new PropertyValue();
    }

    /**
     * Create an instance of {@link CharacteristicFilter }
     * 
     */
    public CharacteristicFilter createCharacteristicFilter() {
        return new CharacteristicFilter();
    }

    /**
     * Create an instance of {@link ObjectVariantResult }
     * 
     */
    public ObjectVariantResult createObjectVariantResult() {
        return new ObjectVariantResult();
    }

    /**
     * Create an instance of {@link ObjectResult }
     * 
     */
    public ObjectResult createObjectResult() {
        return new ObjectResult();
    }

    /**
     * Create an instance of {@link ObjectDataFilter }
     * 
     */
    public ObjectDataFilter createObjectDataFilter() {
        return new ObjectDataFilter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soa.weg.net/eng/maestro/filter", name = "Resquest")
    public JAXBElement<ObjectRequest> createResquest(ObjectRequest value) {
        return new JAXBElement<ObjectRequest>(RESQUEST_QNAME, ObjectRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soa.weg.net/eng/maestro/filter", name = "Response")
    public JAXBElement<ObjectResponse> createResponse(ObjectResponse value) {
        return new JAXBElement<ObjectResponse>(RESPONSE_QNAME, ObjectResponse.class, null, value);
    }

}
