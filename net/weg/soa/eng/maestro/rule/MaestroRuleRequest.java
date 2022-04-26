
package net.weg.soa.eng.maestro.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.weg.eng.xml.type.ObjectContextXmlType;


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
 *         &lt;element name="Language">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="5"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AdditionalData" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ObjectContext" type="{http://soa.weg.net/eng/rule/maestro}ObjectContextXmlType" minOccurs="0"/>
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
    "additionalData",
    "objectContext"
})
@XmlRootElement(name = "MaestroRuleRequest")
public class MaestroRuleRequest {

    @XmlElement(name = "User", required = true)
    protected String user;
    @XmlElement(name = "Language", required = true)
    protected String language;
    @XmlElement(name = "AdditionalData")
    protected Boolean additionalData;
    @XmlElement(name = "ObjectContext")
    protected ObjectContextXmlType objectContext;

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
     * Gets the value of the additionalData property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAdditionalData() {
        return additionalData;
    }

    /**
     * Sets the value of the additionalData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAdditionalData(Boolean value) {
        this.additionalData = value;
    }

    /**
     * Gets the value of the objectContext property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectContextXmlType }
     *     
     */
    public ObjectContextXmlType getObjectContext() {
        return objectContext;
    }

    /**
     * Sets the value of the objectContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectContextXmlType }
     *     
     */
    public void setObjectContext(ObjectContextXmlType value) {
        this.objectContext = value;
    }

}
