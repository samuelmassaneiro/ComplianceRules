package _3716;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class GenericRule1561983482875 {

    private static final GenericRule1561983482875 INSTANCE = new GenericRule1561983482875();

    public static GenericRule1561983482875 getInstance() {
        return GenericRule1561983482875.INSTANCE;
    }

    public boolean execute(ComponentBase _this)
    {
        double pot = (double) _this.get("ZPOTENCIA_ESTATISTICA_CV_01");
        double ten = (double) _this.get("V_MAIOR_TENSAO_01");
        boolean atendeEv = false;

        if (pot >= 1 && pot <= 500 ) atendeEv = true;
        if (atendeEv && ten > 600) atendeEv = false;

        return atendeEv;
    }

}