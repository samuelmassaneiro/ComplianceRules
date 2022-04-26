package _7947;

import net.weg.eng.service.object.ComponentBase;

import java.nio.charset.StandardCharsets;

public class GenericRule1637685406608 {

    private static final GenericRule1637685406608 INSTANCE = new GenericRule1637685406608();

    public static GenericRule1637685406608 getInstance() {
        return GenericRule1637685406608.INSTANCE;
    }
    public boolean execute(final ComponentBase _this) {
        String value = "";
        if (_this.getObjectValue("V_MAIOR_FREQUENCIA_01").getCharacteristicValue() != null) {
            if (_this.getObjectValue("V_MAIOR_FREQUENCIA_01").getCharacteristicValue().getCharacteristicValueLanguages().size() != 0) {
                value = _this.getObjectValue("V_MAIOR_FREQUENCIA_01").getCharacteristicValue().getCharacteristicValueLanguages().get("pt").getDescription();
            } else {
                value = _this.string("V_MAIOR_FREQUENCIA_01");
            }
        } else {
            value = _this.string("V_MAIOR_FREQUENCIA_01");
        }
        String valueString[];
        valueString = value.replaceAll("[^\\d\\s\\p{Z}./-]", "").split("[\\s\\p{Z}/-]");

        String valueArrayString = "60.0";
        return !value.equals(valueArrayString);
        //return !value.equals(valueArrayString.toUpperCase().getBytes(StandardCharsets.UTF_8).length);
    }
}