package _3185;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1569257010599 {

    private static final GenericRule1569257010599 INSTANCE = new GenericRule1569257010599();

    public static GenericRule1569257010599 getInstance() {
        return GenericRule1569257010599.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double temp  = (double) _this.get("ZTEMPERATURA_AMBIENTE_MAX_01");
        String prot = "00001";
        try{prot = _this.get("ZPROTETOR_TERMICO_FENOLICO_01").toString();}catch(Exception e){}
        double tempMax = 75;
        if (!prot.equals("00001")) tempMax = 50;
        return temp > tempMax;
    }

}