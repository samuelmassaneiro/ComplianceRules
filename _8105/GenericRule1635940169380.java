package _8105;

import net.weg.eng.service.object.ComponentBase;
import java.util.*;

public class GenericRule1635940169380 {

    private static final GenericRule1635940169380 INSTANCE = new GenericRule1635940169380();

    public static GenericRule1635940169380 getInstance() {
        return GenericRule1635940169380.INSTANCE;
    }
    public void execute(ComponentBase _this) {
        List logList = new ArrayList<>();
        logList = (List)_this.get("LOG_CERTIFICACAO_02");
        _this.set("LOG_CERTIFICACAO_02", null, true);
        for(Object logValue : logList){
            if(logValue != null){
                if(!(logValue.toString().contains("CENTRO_PRODUCAO") && logValue.toString().contains("CSA CLASS"))){
                    _this.util().add("LOG_CERTIFICACAO_02",logValue);
                }
            }
        }
    }
}