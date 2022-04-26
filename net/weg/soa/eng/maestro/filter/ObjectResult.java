
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectResult", propOrder = {
    "id",
    "objectVariant"
})
public class ObjectResult {

    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(name = "ObjectVariant")
    protected List<ObjectVariantResult> objectVariant;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public List<ObjectVariantResult> getObjectVariant() {
        if (objectVariant == null) {
            objectVariant = new ArrayList<ObjectVariantResult>();
        }
        return this.objectVariant;
    }

}
