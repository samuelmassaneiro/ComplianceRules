package _7908;


import java.util.*;

import net.weg.eng.service.object.ComponentBase;

import java.util.Arrays;
import java.util.List;
public class GenericRule1638629260588 {

    private static final GenericRule1638629260588 INSTANCE = new GenericRule1638629260588();

    public static GenericRule1638629260588 getInstance() {
        return GenericRule1638629260588.INSTANCE;
    }
    public boolean execute(final ComponentBase _this) {
        String value = "";
        if (_this.getObjectValue("ZDETALHE1_01").getCharacteristicValue() != null) {
            if (_this.getObjectValue("ZDETALHE1_01").getCharacteristicValue().getCharacteristicValueLanguages().size() != 0) {
                value = _this.getObjectValue("ZDETALHE1_01").getCharacteristicValue().getCharacteristicValueLanguages().get("pt").getDescription();
            } else {
                value = _this.string("ZDETALHE1_01");
            }
        } else {
            value = _this.string("ZDETALHE1_01");
        }
        String valueString[];
        valueString = value.replaceAll("[^\\d\\s\\p{Z}./-]", "").split("[\\s\\p{Z}/-]");

        String valueArrayString = "6400";

        int splitIndex = Integer.parseInt(String.valueOf(Arrays.stream(valueString).count()));

        for (int i = 0; i < splitIndex; i++) {
            if (!(valueString[i].equals("") || valueString[i].equals("."))) {
                if (!(valueString[i].matches(".*\\..*\\..*"))) {
                    if ((Double.parseDouble(valueString[i])
                            >=                        Double.parseDouble(valueArrayString))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}