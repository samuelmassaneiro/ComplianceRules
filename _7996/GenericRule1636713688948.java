package _7996;

import net.weg.eng.service.object.ComponentBase;

import java.util.List;
import java.util.ArrayList;

public class GenericRule1636713688948 {

    private static final GenericRule1636713688948 INSTANCE = new GenericRule1636713688948();
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

    public static GenericRule1636713688948 getInstance() {
        return GenericRule1636713688948.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        ComponentBase vc = _this.getCmp("ZCONF_COMPLIANCE->sec_dados_container->sec_transformed");
        ComponentBase zconf = _this.getCmp("ZCONF_COMPLIANCE");

        int plant = vc.number("ZCENTRO_VC_01").intValue();
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
                    zconf.getMessages().error(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00311"));
                } else {
                    zconf.getMessages().alert(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00311"));
                }
            }
        }
    }
}