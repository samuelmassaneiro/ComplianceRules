package _3210;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1622037819594 {

    private static final GenericRule1622037819594 INSTANCE = new GenericRule1622037819594();

    public static GenericRule1622037819594 getInstance() {
        return GenericRule1622037819594.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double A = 0;
        double B = 0;
        double C = 0;
        double D = 0;
        double E = 0;
        A = Double.parseDouble(_this.get("VAR_TEMP_01").toString());
        B = Double.parseDouble(_this.get("VAR_TEMP_02").toString());
        C = Double.parseDouble(_this.get("VAR_TEMP_03").toString());
        D = Double.parseDouble(_this.get("VAR_TEMP_04").toString());
        E = Double.parseDouble(_this.get("VAR_TEMP_05").toString());
        _this.util().debug("A*(B+C+D+E) = " + A + "*(" + B + "+" + C + "+" + D + "+" + E + ") = " + A*(B+C+D+E));
        double ret = A*(B+C+D+E);
        return (ret > 0);
    }
}