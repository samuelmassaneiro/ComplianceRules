package _8168;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1640281802712 {

    private static final GenericRule1640281802712 INSTANCE = new GenericRule1640281802712();

    public static GenericRule1640281802712 getInstance() {
        return GenericRule1640281802712.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);

        boolean isCentroVc = transformed.getDescOrValue("ZCENTRO_VC_01").contains("4100");
        boolean isCentroProd = transformed.getDescOrValue("CENTRO_PRODUCAO").contains("4100");
        boolean isSamuel = "samuelpm".equals(_this.getLogin());

        if ((isCentroVc || isCentroProd) & isSamuel) {
            return true;
        } else {
            return false;
        }
    }

}