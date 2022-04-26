
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectVariantResult", propOrder = {
    "objectValue",
    "id",
    "type",
    "status",
    "material"
})
public class ObjectVariantResult {

    @XmlElement(name = "ObjectValue")
    protected List<CharacteristicFilter> objectValue;
    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "Status")
    protected Integer status;
    @XmlElement(name = "Material")
    protected String material;

    public List<CharacteristicFilter> getObjectValue() {
        if (objectValue == null) {
            objectValue = new ArrayList<CharacteristicFilter>();
        }
        return this.objectValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String value) {
        this.material = value;
    }

}
