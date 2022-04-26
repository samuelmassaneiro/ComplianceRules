package _7355;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;
import net.weg.eng.service.object.AssociatedApplicationDecorator;
import net.weg.eng.bean.rule.IMessage;
import net.weg.eng.bean.rule.MessageStatus;

import java.util.List;
import java.util.Arrays;

public class GenericRule1631099832428 {

    private static final GenericRule1631099832428 INSTANCE = new GenericRule1631099832428();
    private static final String COMPLIANCE_ALIAS = "compliance";

    public static GenericRule1631099832428 getInstance() {
        return GenericRule1631099832428.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ComponentBase vc = _this.getCmp("../");
        AssociatedApplicationDecorator associatedApplicationDecorator = (AssociatedApplicationDecorator) vc.executeMethod("getRuleServiceAssociatedApplication");
        ComponentBase compliance = associatedApplicationDecorator.getAssociatedApplications().get(COMPLIANCE_ALIAS).getDecorated(ComponentBase.class);
        List<IMessage> complianceMessages = compliance.getMessages().getAllMessages();
        String ruleId = _this.getCurrentRule().getRuleVersionId().toString();

        ObjectContext arabiaSaudita = compliance.getChildByAliasRecursive("arabia_saudita");

        if (arabiaSaudita != null) {
            String characts = "";

            /*
            String zUsuario = _this.getUser();
            _this.getValueDescription("ZCONTROLE_USUARIO_W22_01");
            _this.setValue("ZCONTROLE_USUARIO_W22_01", _this.getRoot().get("ZCONTROLE_USUARIO_W22_01));
            System.out.println("");
            RuleService ruleClone = new RuleService;
            ruleClone = _this.getCurrentRule().clone;
            */

            characts += "(SMART_CODE = " + arabiaSaudita.get("SMART_CODE") + "), " + "(SIPWMO_VALIDA_SMART_CODE = " + arabiaSaudita.get("SIPWMO_VALIDA_SMART_CODE") + "), (DATA_VALIDADE_SMART_CODE = " + arabiaSaudita.get("DATA_VALIDADE_SMART_CODE") + "), (NIVEL_RENDIMENTO_SASO = " + arabiaSaudita.get("NIVEL_RENDIMENTO_SASO") + ")";

            _this.set("V_FORMULA", characts);
        }

        boolean isConfiguratorTypeError = vc.string("ZLINHA_01_PLACA_ADIC_TAG_01").equals("RRIESE") || Arrays.asList("00001", "00000").contains(vc.string("ZTIPO_CONFIGURADOR_01"));

        if (complianceMessages.size() > 0) {
            vc.getMessages().alert(ruleId + " - " + System.nanoTime(), _this.getTranslate("zvc_wmo_msg_00362").replace("_SYSTEM_", "Compliance"));
            for (IMessage message : complianceMessages) {
                boolean isMessageTypeError = message.getMessage().getStatus() == MessageStatus.Error;
                String complement = isMessageTypeError ? "[" + _this.getTranslate("error").toUpperCase() + "] - " : "";
                String prefix = _this.getTranslate("zvc_wmo_msg_00353").replace("_RULE_", message.getMessage().getTitle());

                String aa = _this.string("V_FORMULA1") + (_this.get("V_FORMULA1") != null ? ";" : "");

                aa += message.getMessage().getDescription();

                _this.set("V_FORMULA1", aa);

                if (isConfiguratorTypeError && isMessageTypeError) {
                    vc.getMessages().error(ruleId + " - " + System.nanoTime(), prefix + message.getMessage().getDescription());
                } else {
                    vc.getMessages().alert(ruleId + " - " + System.nanoTime(), prefix + complement + message.getMessage().getDescription());
                }
            }
            vc.getMessages().alert(ruleId + " - " + System.nanoTime(), _this.getTranslate("zvc_wmo_msg_00363"));
        }

        this.setSaudiArabiaValues(vc, arabiaSaudita);

    }

    public void setSaudiArabiaValues(ObjectContext vc, ObjectContext saudiArabia){
        if(saudiArabia != null){
            vc.executeMethod("setVcValue", "ZREGISTRO_SMARTCODE_SASO_01", saudiArabia.get("ZREGISTRO_SMARTCODE_SASO_01"));
            vc.executeMethod("setVcValue", "ZSTATUS_REGISTRO_SMARTCODE_01", saudiArabia.get("ZSTATUS_REGISTRO_SMARTCODE_01"));
        }
    }
}