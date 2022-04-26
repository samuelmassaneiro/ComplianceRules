
package net.weg.soa.eng.maestro.documentviewer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DocumentViewers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DocumentViewer" type="{http://soa.weg.net/eng/maestro/documentViewer}RequestDocumentViewerType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user",
    "language",
    "documentViewers"
})
@XmlRootElement(name = "DocumentViewerRequest")
public class DocumentViewerRequest {

    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "Language", required = true)
    protected String language;
    @XmlElement(name = "DocumentViewers", required = true)
    protected DocumentViewerRequest.DocumentViewers documentViewers;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the documentViewers property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentViewerRequest.DocumentViewers }
     *     
     */
    public DocumentViewerRequest.DocumentViewers getDocumentViewers() {
        return documentViewers;
    }

    /**
     * Sets the value of the documentViewers property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentViewerRequest.DocumentViewers }
     *     
     */
    public void setDocumentViewers(DocumentViewerRequest.DocumentViewers value) {
        this.documentViewers = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="DocumentViewer" type="{http://soa.weg.net/eng/maestro/documentViewer}RequestDocumentViewerType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "documentViewer"
    })
    public static class DocumentViewers {

        @XmlElement(name = "DocumentViewer", required = true)
        protected List<RequestDocumentViewerType> documentViewer;

        /**
         * Gets the value of the documentViewer property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the documentViewer property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDocumentViewer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RequestDocumentViewerType }
         * 
         * 
         */
        public List<RequestDocumentViewerType> getDocumentViewer() {
            if (documentViewer == null) {
                documentViewer = new ArrayList<RequestDocumentViewerType>();
            }
            return this.documentViewer;
        }

    }

}
