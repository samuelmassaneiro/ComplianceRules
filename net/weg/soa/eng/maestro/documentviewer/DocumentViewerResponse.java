
package net.weg.soa.eng.maestro.documentviewer;

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
 *         &lt;element name="DocumentViewers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "documentViewers"
})
@XmlRootElement(name = "DocumentViewerResponse")
public class DocumentViewerResponse {

    @XmlElement(name = "DocumentViewers", required = true)
    protected DocumentViewerResponse.DocumentViewers documentViewers;

    /**
     * Gets the value of the documentViewers property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentViewerResponse.DocumentViewers }
     *     
     */
    public DocumentViewerResponse.DocumentViewers getDocumentViewers() {
        return documentViewers;
    }

    /**
     * Sets the value of the documentViewers property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentViewerResponse.DocumentViewers }
     *     
     */
    public void setDocumentViewers(DocumentViewerResponse.DocumentViewers value) {
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
     *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "status",
        "error"
    })
    public static class DocumentViewers {

        @XmlElement(name = "Status", required = true)
        protected String status;
        @XmlElement(name = "Error")
        protected String error;

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the error property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getError() {
            return error;
        }

        /**
         * Sets the value of the error property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setError(String value) {
            this.error = value;
        }

    }

}
