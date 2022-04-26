package _3157;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1638276004696 {

    private static final GenericRule1638276004696 INSTANCE = new GenericRule1638276004696();

    public static GenericRule1638276004696 getInstance() {
        return GenericRule1638276004696.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double pot = (double) _this.get("ZPOTENCIA_ESTATISTICA_CV_01");
        double potMax = (double) _this.get("POTENCIA_MAXIMA");
        return pot > potMax;
    }
}