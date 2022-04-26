package _3774;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1578609616115 {

    private static final GenericRule1578609616115 INSTANCE = new GenericRule1578609616115();

    public static GenericRule1578609616115 getInstance() {
        return GenericRule1578609616115.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double potMax = _this.number("V_MAIOR_POTENCIA_CV_60HZ_01");
        double potMin = _this.number("V_MENOR_POTENCIA_CV_60HZ_01");
        return (potMax > 2 || potMin < 0.25);
    }

}