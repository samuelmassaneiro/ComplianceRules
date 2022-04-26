
package net.weg.soa.eng.maestro.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacteristicFilter", propOrder = {
    "name",
    "propertyValue",
    "operator",
     "objectTypeNode"
})
public class CharacteristicFilter {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "PropertyValue")
    protected List<PropertyValue> propertyValue;
    @XmlElement(name = "Operator")
    @XmlSchemaType(name = "NMTOKEN")
    protected OperatorType operator;

   @XmlElement(name = "ObjectTypeNode", required = true)
    protected String objectTypeNode;

    public String getObjectTypeNode() {
        return objectTypeNode;
    }

    public void setObjectTypeNode(String objectTypeNode) {
        this.objectTypeNode = objectTypeNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public void setPropertyValue(List<PropertyValue> propertyValue) {
        this.propertyValue = propertyValue;
    }

    public List<PropertyValue> getPropertyValue() {
        if (propertyValue == null) {
            propertyValue = new ArrayList<PropertyValue>();
        }
        return this.propertyValue;
    }

    public OperatorType getOperator() {
        return operator;
    }

    public void setOperator(OperatorType value) {
        this.operator = value;
    }

}
