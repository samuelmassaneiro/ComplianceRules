
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ObjectResponse complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteudo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ObjectResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObjectHeader" type="{http://soa.weg.net/eng/maestro/filter}ObjectResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectResponse", propOrder = {
    "objectHeader"
})
public class ObjectResponse {

    @XmlElement(name = "ObjectHeader")
    protected List<ObjectResult> objectHeader;

    /**
     * Gets the value of the objectHeader property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectHeader property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectResult }
     * 
     * 
     */
    public List<ObjectResult> getObjectHeader() {
        if (objectHeader == null) {
            objectHeader = new ArrayList<ObjectResult>();
        }
        return this.objectHeader;
    }

}
