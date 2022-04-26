
package net.weg.soa.eng.maestro.method;

import javax.xml.bind.annotation.XmlRegistry;

import net.weg.eng.xml.type.CharacteristicXmlType;
import net.weg.eng.xml.type.EventXmlType;
import net.weg.eng.xml.type.MaestroMethodRequest;
import net.weg.eng.xml.type.MaestroMethodResponse;
import net.weg.eng.xml.type.MessageXmlType;
import net.weg.eng.xml.type.MethodXmlType;
import net.weg.eng.xml.type.ObjectContextXmlType;
import net.weg.eng.xml.type.ObjectHeaderXmlType;
import net.weg.eng.xml.type.ObjectTypeXmlType;
import net.weg.eng.xml.type.ObjectValueXmlType;
import net.weg.eng.xml.type.ObjectVariantHistXmlType;
import net.weg.eng.xml.type.ObjectVariantXmlType;
import net.weg.eng.xml.type.ParameterXmlType;
import net.weg.eng.xml.type.PropertyValueXmlType;
import net.weg.eng.xml.type.RuleActionXmlType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.weg.soa.eng.maestro.method package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.weg.soa.eng.maestro.method
     * 
     */
    public ObjectFactory() {
    	super();
    }

    /**
     * Create an instance of {@link ParameterXmlType }
     * 
     */
    public ParameterXmlType createParameterXmlType() {
        return new ParameterXmlType();
    }

    /**
     * Create an instance of {@link RuleActionXmlType }
     * 
     */
    public RuleActionXmlType createRuleActionXmlType() {
        return new RuleActionXmlType();
    }

    /**
     * Create an instance of {@link ObjectTypeXmlType }
     * 
     */
    public ObjectTypeXmlType createObjectTypeXmlType() {
        return new ObjectTypeXmlType();
    }

    /**
     * Create an instance of {@link ObjectHeaderXmlType }
     * 
     */
    public ObjectHeaderXmlType createObjectHeaderXmlType() {
        return new ObjectHeaderXmlType();
    }

    /**
     * Create an instance of {@link MessageXmlType }
     * 
     */
    public MessageXmlType createMessageXmlType() {
        return new MessageXmlType();
    }

    /**
     * Create an instance of {@link MethodXmlType }
     * 
     */
    public MethodXmlType createMethodXmlType() {
        return new MethodXmlType();
    }

    /**
     * Create an instance of {@link ObjectContextXmlType }
     * 
     */
    public ObjectContextXmlType createObjectContextXmlType() {
        return new ObjectContextXmlType();
    }

    /**
     * Create an instance of {@link MaestroMethodRequest }
     * 
     */
    public MaestroMethodRequest createMaestroMethodRequest() {
        return new MaestroMethodRequest();
    }

    /**
     * Create an instance of {@link ObjectValueXmlType }
     * 
     */
    public ObjectValueXmlType createObjectValueXmlType() {
        return new ObjectValueXmlType();
    }

    /**
     * Create an instance of {@link PropertyValueXmlType }
     * 
     */
    public PropertyValueXmlType createPropertyValueXmlType() {
        return new PropertyValueXmlType();
    }

    /**
     * Create an instance of {@link ObjectVariantHistXmlType }
     * 
     */
    public ObjectVariantHistXmlType createObjectVariantHistXmlType() {
        return new ObjectVariantHistXmlType();
    }

    /**
     * Create an instance of {@link ObjectVariantXmlType }
     * 
     */
    public ObjectVariantXmlType createObjectVariantXmlType() {
        return new ObjectVariantXmlType();
    }

    /**
     * Create an instance of {@link CharacteristicXmlType }
     * 
     */
    public CharacteristicXmlType createCharacteristicXmlType() {
        return new CharacteristicXmlType();
    }

    /**
     * Create an instance of {@link MaestroMethodResponse }
     * 
     */
    public MaestroMethodResponse createMaestroMethodResponse() {
        return new MaestroMethodResponse();
    }

    /**
     * Create an instance of {@link EventXmlType }
     * 
     */
    public EventXmlType createEventXmlType() {
        return new EventXmlType();
    }

}
