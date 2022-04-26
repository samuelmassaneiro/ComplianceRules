package _3716;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1588083631209 {

    private static final GenericRule1588083631209 INSTANCE = new GenericRule1588083631209();

    public static GenericRule1588083631209 getInstance() {
        return GenericRule1588083631209.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {
        double maiorFrequencia = _this.number("V_MAIOR_FREQUENCIA_01");
        double menorFrequencia = _this.number("V_MENOR_FREQUENCIA_01");
        return (menorFrequencia == 60D && maiorFrequencia == 60D)
                || (menorFrequencia == 50D || maiorFrequencia == 60D);
    }

}