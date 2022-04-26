
package net.weg.soa.eng.maestro.filter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyValue", propOrder = {
    "value",
    "description"
})
public class PropertyValue {

    @XmlElement(name = "Value")
    protected String value;
    @XmlElement(name = "Description")
    protected String description;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

}
