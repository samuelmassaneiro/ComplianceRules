
package net.weg.soa.eng.maestro.documentviewer;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.weg.soa.eng.maestro.documentviewer package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.weg.soa.eng.maestro.documentviewer
     * 
     */
    public ObjectFactory() {
    	super();
    }

    /**
     * Create an instance of {@link DocumentViewerRequest }
     * 
     */
    public DocumentViewerRequest createDocumentViewerRequest() {
        return new DocumentViewerRequest();
    }

    /**
     * Create an instance of {@link DocumentViewerResponse }
     * 
     */
    public DocumentViewerResponse createDocumentViewerResponse() {
        return new DocumentViewerResponse();
    }

    /**
     * Create an instance of {@link RequestDocumentViewerType }
     * 
     */
    public RequestDocumentViewerType createRequestDocumentViewerType() {
        return new RequestDocumentViewerType();
    }

    /**
     * Create an instance of {@link DocumentViewerRequest.DocumentViewers }
     * 
     */
    public DocumentViewerRequest.DocumentViewers createDocumentViewerRequestDocumentViewers() {
        return new DocumentViewerRequest.DocumentViewers();
    }

    /**
     * Create an instance of {@link DocumentViewerResponse.DocumentViewers }
     * 
     */
    public DocumentViewerResponse.DocumentViewers createDocumentViewerResponseDocumentViewers() {
        return new DocumentViewerResponse.DocumentViewers();
    }

}
