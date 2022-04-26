package _4024;

import net.weg.eng.service.object.ComponentBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class GenericRule1525958950434 {

    private static final GenericRule1525958950434 INSTANCE = new GenericRule1525958950434();

    public static GenericRule1525958950434 getInstance() {
        return GenericRule1525958950434.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        String tensao;
        Integer menorTensao;
        Integer maiorTensao;
        try {
            tensao = _this.getValueDescription("ZTENSAO_SEM_CABO_01").toString();
            menorTensao = selectVolt(tensao, false);
            maiorTensao = selectVolt(tensao, true);
            _this.set("V_MENOR_TENSAO_01", menorTensao, true);
            _this.set("V_MAIOR_TENSAO_01", maiorTensao, true);
            _this.set("ZMAIOR_TENSAO_MOTOR_01", maiorTensao, true);
        } catch (Exception e) {
            _this.set("V_MENOR_TENSAO_01", 0, true);
            _this.set("V_MAIOR_TENSAO_01", 0, true);
            _this.set("ZMAIOR_TENSAO_MOTOR_01", 0, true);
            _this.util().debug(e.toString());
        }
    }

    public static Integer selectVolt(String voltage, boolean maior) {
        voltage = voltage.replace("V","");
        String caracteres = "/- ";
        ArrayList < Integer > volts = new ArrayList < Integer > ();
        String[] parts = voltage.toUpperCase().split("C");
        voltage = parts[0];
        parts = voltage.split("[" + Pattern.quote(caracteres) + "]");

        for (String part: parts) {
            try {
                volts.add(Integer.parseInt(part));
            } catch (Exception e) {

            }
        }
        Collections.sort(volts);
        if (maior) {
            return volts.get(volts.size() - 1);
        } else {
            return volts.get(0);
        }
    }
}
