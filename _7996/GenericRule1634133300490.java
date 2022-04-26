package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

public class GenericRule1634133300490 {

    private static final GenericRule1634133300490 INSTANCE = new GenericRule1634133300490();

    public static GenericRule1634133300490 getInstance() {
        return GenericRule1634133300490.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ObjectContext zconf = _this.getRoot();
        ObjectContext certificacaoCompliance = zconf.getChildByAliasRecursive("certificacao_compliance");
        ObjectContext transformed = zconf.getChildByAliasRecursive("certificacao_motor");
        if (certificacaoCompliance != null) {
            String message = _this.getTranslate("zvc_wmo_msg_00087");
            message = message.replace("_TC1_", (CharSequence) transformed.get("TC1"));
            if (transformed.get("TC2") != null){
                message = message.replace("_TC2_", (CharSequence) transformed.get("TC2"));
            } else {
                message = message.replace("_TC2_", "");
            }
            if (transformed.get("SIPWMO_TC_INVERSOR") != null){
                message = message.replace("_TCINVERSOR_", (CharSequence) transformed.get("SIPWMO_TC_INVERSOR"));
            } else{
                message = message.replace("_TCINVERSOR_", "");
            }
            zconf.getMessages().alert(_this.getCurrentRule().getRuleVersionId().toString(), message);
        }
    }
}
