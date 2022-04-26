package _7820;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1646415126460 {

    private static final GenericRule1646415126460 INSTANCE = new GenericRule1646415126460();

    public static GenericRule1646415126460 getInstance() {
        return GenericRule1646415126460.INSTANCE;
    }

    public void execute(final ComponentBase _this) {

        String dt = "";
        String node = "";
        double deltaTemp = 0.0;

        if(_this.getRoot().getAlias().equals("RULE_SERVICE_VC_10000008")) node = "../proj_elet";
        if(_this.getRoot().getAlias().equals("ZCONF_COMPLIANCE")) node = "../../projeto_eletrico";

        _this.util().debug("NODE =" + node);
        _this.util().debug("NODE =" + node);

        if(_this.getCmp(node).get("ZELEVACAO_TEMPERATURA_01") != null){
            deltaTemp = _this.getCmp(node).number("ZELEVACAO_TEMPERATURA_01");
        }
        else{
            deltaTemp = _this.number("ZELEVACAO_TEMPERATURA_01");
        }

        _this.util().debug("DELTATEMP = " + deltaTemp);

    }

}