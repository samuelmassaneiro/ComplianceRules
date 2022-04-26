package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

public class GenericRule1636636783206 {

    private static final GenericRule1636636783206 INSTANCE = new GenericRule1636636783206();

    public static GenericRule1636636783206 getInstance() {
        return GenericRule1636636783206.INSTANCE;
    }

    public boolean isVoltageWithFrequencyInScope(ComponentBase vc) {
        String commercialFrequency = vc.util().getValueDescription("ZFREQUENCIA_COMERCIAL_01") != null ? vc.util().getValueDescription("ZFREQUENCIA_COMERCIAL_01") : vc.string("ZFREQUENCIA_COMERCIAL_01");

        boolean firstCondition = !(vc.string("ZFREQUENCIA_01").equals("00064")
                && vc.util().getValueDescription("ZTENSAO_SEM_CABO_01") != null
                && !vc.util().getValueDescription("ZTENSAO_SEM_CABO_01").contains("//"));

        boolean secondCondition = !(vc.string("ZFREQUENCIA_01").equals("00064")
                && vc.util().getValueDescription("ZTENSAO_SEM_CABO_01") != null
                && vc.util().getValueDescription("ZTENSAO_SEM_CABO_01").contains("//")
                && vc.number("V_MAIOR_TENSAO_60HZ_01") > 600);

        boolean thirdCondition = !(vc.string("ZFREQUENCIA_01").equals("00079")
                && vc.number("V_MAIOR_TENSAO_60HZ_01") > 600);

        boolean fourthCondition = !(!commercialFrequency.equals("") && !commercialFrequency.contains("60"));

        return firstCondition && secondCondition && thirdCondition && fourthCondition;
    }

    public boolean execute(final ComponentBase _this) {
        ComponentBase vc = _this.getCmp("ZCONF_COMPLIANCE->sec_dados_container->sec_transformed");
        boolean hasValidEnclosure = vc.string("ZINVOLUCRO_01").equals("00003") || vc.string("ZINVOLUCRO_01").equals("00001"); // Fechado
        boolean hasVoltageWithFrequencyInScope = isVoltageWithFrequencyInScope(vc);
        boolean hasValidServiceRegime = vc.string("ZREGIME_SERVICO_01").equals("00003") || vc.string("ZREGIME_SERVICO_01").equals("00015");
        boolean hasValidDetail = vc.string("ZDETALHE2_01").equals("00004") || vc.string("ZDETALHE2_01").equals("00012")
                || vc.string("ZDETALHE2_01").equals("00013") || vc.string("ZDETALHE2_01").equals("00026")
                || vc.string("ZDETALHE2_01").equals("00032") || vc.string("ZDETALHE2_01").equals("00039");
        boolean hasValidPower = vc.number("ZPOTENCIA_ESTATISTICA_CV_01") >= 1
                && vc.number("ZPOTENCIA_ESTATISTICA_CV_01") <= 500;
        boolean hasValidPolarity = vc.string("ZPOLARIDADE_COMPLETA_01").equals("00001") || vc.string("ZPOLARIDADE_COMPLETA_01").equals("00003")
                || vc.string("ZPOLARIDADE_COMPLETA_01").equals("00009") || vc.string("ZPOLARIDADE_COMPLETA_01").equals("00016"); // 2, 4, 6 e 8 polos
        boolean isNomAnce = vc.get("ZCERTIFICACAO_01") != null && vc.util().desc("ZCERTIFICACAO_01", "pt").contains("NOM-ANCE");
        return isNomAnce && !(hasValidEnclosure && hasVoltageWithFrequencyInScope && hasValidServiceRegime && hasValidDetail && hasValidPower && hasValidPolarity);
    }
}
