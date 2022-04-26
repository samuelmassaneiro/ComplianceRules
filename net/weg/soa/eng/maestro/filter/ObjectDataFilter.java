
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectDataFilter", propOrder = {
    "type",
    "valueCondition"
})
public class ObjectDataFilter {

    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "ValueCondition")
    protected List<String> valueCondition;

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public List<String> getValueCondition() {
        if (valueCondition == null) {
            valueCondition = new ArrayList<String>();
        }
        return this.valueCondition;
    }

}
