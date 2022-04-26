package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.ArrayList;
import java.util.List;

public class GenericRule1637946413212 {

    private static final GenericRule1637946413212 INSTANCE = new GenericRule1637946413212();

    public static GenericRule1637946413212 getInstance() {
        return GenericRule1637946413212.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ComponentBase zconf = _this.getCmp("ZCONF_COMPLIANCE");
        ComponentBase variantConfiguration = _this.getCmp("ZCONF_COMPLIANCE->sec_dados_container->sec_transformed");
        ObjectContext certificacaoMotor = _this.getRoot().getChildByAliasRecursive("certificacao_motor");
        ComponentBase certificacaoCompliance = _this.getCmp("ZCONF_COMPLIANCE->sec_certificacao_container->certificacao_compliance");
        String grouperType = variantConfiguration.string("ZTIPO_CONFIGURADOR_01");

        List certificadosCM = null;

        if (certificacaoMotor != null && certificacaoCompliance != null) {
            certificadosCM = (List) certificacaoCompliance.get("COMPLIANCE_CERTIFICADOS_ATENDIDOS");
            if(!certificadosCM.contains("00015") & "00001".equals(grouperType)){
                zconf.getMessages().error(" " + _this.getCurrentRule().getRuleVersionId().toString(),
                        "ERRO: Este motor está fora do escopo de certificação CCC. Remover CCC do campo certificações.");
            }else if(!certificadosCM.contains("00015") & !"00001".equals(grouperType)){
                zconf.getMessages().alert(" " + _this.getCurrentRule().getRuleVersionId().toString(),
                        "ERRO: Este motor está fora do escopo de certificação CCC. Remover CCC do campo certificações.");
            }
        }
    }

}