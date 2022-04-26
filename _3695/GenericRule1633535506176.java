package _3695;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.List;

public class GenericRule1633535506176 {

    private static final GenericRule1633535506176 INSTANCE = new GenericRule1633535506176();

    public static GenericRule1633535506176 getInstance() {
        return GenericRule1633535506176.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ObjectContext variantConfigurator =  _this.getRoot();
        ObjectContext motorCertification = _this.getRoot().getChildByAliasRecursive("certificacao_motor");
        List<String> certifications = motorCertification.getListString("ZCERTIFICACAO_MOTOR_01");

        //Contains CE
        if (certifications.contains("00007")) {
            variantConfigurator.getMessages().error(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00415"));
        } else {
            variantConfigurator.getMessages().error(_this.getCurrentRule().getRuleVersionId().toString(), _this.getTranslate("zvc_wmo_msg_00416"));
        }
    }

}