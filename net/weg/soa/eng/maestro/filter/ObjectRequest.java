
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ObjectRequest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ObjectRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Criteria" type="{http://soa.weg.net/eng/maestro/filter}CharacteristicFilter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ObjectDataFilter" type="{http://soa.weg.net/eng/maestro/filter}ObjectDataFilter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ECM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ObjectTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ResponseName" type="{http://soa.weg.net/eng/maestro/filter}CharacteristicFilter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectRequest", propOrder = {
    "criteria",
    "objectDataFilter",
    "ecm",
    "description",
    "objectTypeId",
    "responseName"
})
public class ObjectRequest {

    @XmlElement(name = "Criteria")
    protected List<CharacteristicFilter> criteria;
    @XmlElement(name = "ObjectDataFilter")
    protected List<ObjectDataFilter> objectDataFilter;
    @XmlElement(name = "ECM")
    protected String ecm;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ObjectTypeId")
    protected long objectTypeId;
    @XmlElement(name = "ResponseName")
    protected List<CharacteristicFilter> responseName;

    /**
     * Gets the value of the criteria property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criteria property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriteria().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacteristicFilter }
     * 
     * 
     */
    public List<CharacteristicFilter> getCriteria() {
        if (criteria == null) {
            criteria = new ArrayList<CharacteristicFilter>();
        }
        return this.criteria;
    }

    /**
     * Gets the value of the objectDataFilter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectDataFilter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectDataFilter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectDataFilter }
     * 
     * 
     */
    public List<ObjectDataFilter> getObjectDataFilter() {
        if (objectDataFilter == null) {
            objectDataFilter = new ArrayList<ObjectDataFilter>();
        }
        return this.objectDataFilter;
    }

    /**
     * Obt�m o valor da propriedade ecm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getECM() {
        return ecm;
    }

    /**
     * Define o valor da propriedade ecm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setECM(String value) {
        this.ecm = value;
    }

    /**
     * Obt�m o valor da propriedade description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define o valor da propriedade description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obt�m o valor da propriedade objectTypeId.
     * 
     */
    public long getObjectTypeId() {
        return objectTypeId;
    }

    /**
     * Define o valor da propriedade objectTypeId.
     * 
     */
    public void setObjectTypeId(long value) {
        this.objectTypeId = value;
    }

    /**
     * Gets the value of the responseName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacteristicFilter }
     * 
     * 
     */
    public List<CharacteristicFilter> getResponseName() {
        if (responseName == null) {
            responseName = new ArrayList<CharacteristicFilter>();
        }
        return this.responseName;
    }

}
