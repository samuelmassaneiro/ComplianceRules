package net.weg.soa.eng.maestro.rule;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.weg.eng.xml.type.ObjectContextXmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectContext" type="{http://soa.weg.net/eng/rule/maestro}ObjectContextXmlType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "objectContext" })
@XmlRootElement(name = "MaestroRuleResponse")
public class MaestroRuleResponse {

	@XmlElement(name = "ObjectContext")
	protected ObjectContextXmlType objectContext;

	/**
	 * Gets the value of the objectContext property.
	 * @return possible object is {@link ObjectContextXmlType }
	 */
	public ObjectContextXmlType getObjectContext() {
		return objectContext;
	}

	/**
	 * Sets the value of the objectContext property.
	 * @param value allowed object is {@link ObjectContextXmlType }
	 */
	public void setObjectContext(ObjectContextXmlType value) {
		this.objectContext = value;
	}

}
