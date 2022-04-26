
package net.weg.soa.eng.maestro.filter;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OperatorType")
@XmlEnum
public enum OperatorType {

    @XmlEnumValue("Equality")
    EQUALITY("Equality"),
    @XmlEnumValue("Inequality")
    INEQUALITY("Inequality"),
    @XmlEnumValue("GreaterThan")
    GREATER_THAN("GreaterThan"),
    @XmlEnumValue("LessThan")
    LESS_THAN("LessThan"),
    @XmlEnumValue("GreaterThanOrEqualTo")
    GREATER_THAN_OR_EQUAL_TO("GreaterThanOrEqualTo"),
    @XmlEnumValue("LessThanOrEqualTo")
    LESS_THAN_OR_EQUAL_TO("LessThanOrEqualTo");
    private final String value;

    OperatorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatorType fromValue(String v) {
        for (OperatorType c: OperatorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
