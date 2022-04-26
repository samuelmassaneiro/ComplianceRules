package _3185;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1500564885706 {

    private static final GenericRule1500564885706 INSTANCE = new GenericRule1500564885706();

    public static GenericRule1500564885706 getInstance() {
        return GenericRule1500564885706.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        String sProt = "99999";
        try{sProt = _this.get("ZPROT_FENOLICO_CODIGO_PLACA_01").toString();}catch(Exception e){}
        return sProt.equals("99999");
    }

}