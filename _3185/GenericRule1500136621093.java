package _3185;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.filter.ObjectFilterDynamic;
import net.weg.eng.bean.rule.OperatorType;
import net.weg.eng.bean.object.ObjectHeader;
import java.util.List;

public class GenericRule1500136621093 {

    private static final GenericRule1500136621093 INSTANCE = new GenericRule1500136621093();

    public static GenericRule1500136621093 getInstance() {
        return GenericRule1500136621093.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double potMax = 0.0;
        double p = 0;
        String frameGroup = "00000";
        String frame = "00000";
        try{frameGroup = _this.get("ZGRUPO_CARCACA_01").toString();}catch(Exception e){}
        try{frame = _this.get("ZCARCACA_COM_COMPLEMENTO_01").toString();}catch(Exception e){}

        if (frame.equals("00226")) frameGroup = "00076";
        ObjectFilterDynamic filter = new ObjectFilterDynamic("LINK_POTENCIA_MAXIMA_CERTIFICADO");
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "VAR_TEMP_05", OperatorType.Equality, _this.get("VAR_TEMP_05"));
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "ZFAMILIA_PRODUTO_MOTOR_01", OperatorType.Equality, _this.get("ZFAMILIA_PRODUTO_MOTOR_01"));
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "ZINVOLUCRO_01", OperatorType.Equality, _this.get("ZINVOLUCRO_01"));
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "ZFREQUENCIA_01", OperatorType.Equality, _this.get("ZFREQUENCIA_01"));
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "ZGRUPO_CARCACA_01", OperatorType.Equality, frameGroup);
        filter.add("LINK_POTENCIA_MAXIMA_CERTIFICADO", "LINK_POTENCIA_MAXIMA_CERTIFICADO", "POTENCIA_MAXIMA", OperatorType.Equality);
        List <ObjectHeader> results = null;
        try
        {
            results = filter.filter().getResults();
        }catch(Exception e)
        {
            potMax = 0.0;
        }

        if (results != null)
        {
            for(ObjectHeader result : results)
            {
                p = (double) result.getObjectContext().get("POTENCIA_MAXIMA");
                if (p > potMax) potMax = p;
            }
        }
        double pot  = (double) _this.get("ZPOTENCIA_ESTATISTICA_CV_01");
        double fs = (double)  _this.get("YFATOR_SERVICO");
        return pot*fs > potMax;
    }

}