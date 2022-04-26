package _7996;

import com.sun.star.util.Time;
import net.weg.eng.service.object.ComponentBase;
import net.weg.maestro.solr.bean.SimpleData;
import net.weg.soa.serviceclient.sales.salesorderbyidquery.DateTime;
import sun.plugin.util.UserProfile;

import javax.xml.registry.infomodel.User;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import static java.lang.System.currentTimeMillis;

public class GenericRule1637675360262 {

    private static final GenericRule1637675360262 INSTANCE = new GenericRule1637675360262();
    private static final List<Integer> errorPlants, warningPlants;

    static {
        errorPlants = new ArrayList<>();
        errorPlants.add(1100);
        errorPlants.add(1106);

        warningPlants = new ArrayList<>();
        warningPlants.add(4103);
        warningPlants.add(4980);
        warningPlants.add(4981);
        warningPlants.add(4982);
        warningPlants.add(4983);
        warningPlants.add(4986);
        warningPlants.add(4987);
        warningPlants.add(4988);
        warningPlants.add(6981);
    }

    public static GenericRule1637675360262 getInstance() {
        return GenericRule1637675360262.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        ComponentBase vc = _this.getCmp("ZCONF_COMPLIANCE->sec_dados_container->sec_transformed");
        ComponentBase zconf = _this.getCmp("ZCONF_COMPLIANCE");

        int plant = vc.number("CENTRO_PRODUCAO").intValue();
        List<Double> polarities = vc.getListNumber("YPOLARIDADE");
        boolean inScope = true;

        for (int i = 0; i < polarities.size(); ++i) {
            Double result = (120 * vc.number("V_MAIOR_FREQUENCIA_01") / polarities.get(i)) * (1.1 / 1500);

            if (vc.number(i == 0 ? "V_MENOR_POTENCIA_KW_01" : "V_MAIOR_POTENCIA_KW_01") > result) {
                inScope = false;
                break;
            }
        }

        if (inScope) {
            if (errorPlants.contains(plant) || warningPlants.contains(plant)) {
                boolean isError = errorPlants.contains(plant);
                if (isError == true){
                    zconf.getMessages().error(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00600"));
                } else {
                    zconf.getMessages().alert(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00600"));
                }
            }
        }
    }
}