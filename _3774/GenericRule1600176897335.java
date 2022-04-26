package _3774;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1600176897335 {

    private static final GenericRule1600176897335 INSTANCE = new GenericRule1600176897335();

    public static GenericRule1600176897335 getInstance() {
        return GenericRule1600176897335.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        _this.util().add("LOG_CERTIFICACAO_01", "NOM-ANCE#902741#ZFREQUENCIA_01:" + _this.getValueDescription("ZFREQUENCIA_01").toString() + "#ESPERADO: 60 Hz");
    }
}