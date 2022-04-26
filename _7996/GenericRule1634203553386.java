package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.Arrays;

public class GenericRule1634203553386 {

    private static final GenericRule1634203553386 INSTANCE = new GenericRule1634203553386();

    public static GenericRule1634203553386 getInstance() {
        return GenericRule1634203553386.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {
        ObjectContext variantConfigurator =  _this.getRoot().getChildByAliasRecursive("sec_transformed");
        boolean isNomAnce = variantConfigurator.get("ZCERTIFICACAO_01") != null && variantConfigurator.getValueDescription("ZCERTIFICACAO_01").toString().contains("NOM-ANCE");
        boolean hasValidPolarity = Arrays.asList("00001", "00003").contains(variantConfigurator.get("ZPOLARIDADE_COMPLETA_01").toString());
        boolean hasValidServiceRegime = variantConfigurator.get("ZREGIME_SERVICO_01").toString().equals("00003");
        boolean hasValidFrequency = variantConfigurator.get("ZFREQUENCIA_01").toString().equals("00079");
        boolean hasValidVoltage = Double.parseDouble(variantConfigurator.get("V_MAIOR_TENSAO_01").toString()) >= 110 && Double.parseDouble(variantConfigurator.get("V_MAIOR_TENSAO_01").toString()) <= 230;
        boolean hasValidEnclosureMaterial = variantConfigurator.get("ZMATERIAL_INVOLUCRO_01").toString().equals("00001");
        boolean hasValidPower = Double.parseDouble(variantConfigurator.get("ZPOTENCIA_ESTATISTICA_CV_01").toString()) >= 0.25 && Double.parseDouble(variantConfigurator.get("ZPOTENCIA_ESTATISTICA_CV_01").toString()) <= 2;

        boolean hasInquiry = variantConfigurator.get("ZNUMERO_CONSULTA_TECNICA_01") != null;
        boolean isNotDocumentationNecessary = variantConfigurator.get("ZMERCADO_01").toString().equals("00012") ? true :  hasInquiry;
        boolean hasNotCommercialFrequency = variantConfigurator.get("ZFREQUENCIA_COMERCIAL_01") == null;

        _this.util().debug("isNomAnce: " + isNomAnce);
        _this.util().debug("hasValidPolarity: " + hasValidPolarity);
        _this.util().debug("hasValidServiceRegime: " + hasValidServiceRegime);
        _this.util().debug("hasValidFrequency: " + isNomAnce);
        _this.util().debug("hasValidVoltage: " + hasValidVoltage);
        _this.util().debug("hasValidEnclosureMaterial: " + hasValidEnclosureMaterial);
        _this.util().debug("hasValidPower: " + hasValidPower);

        _this.util().debug("hasInquiry: " + hasInquiry);
        _this.util().debug("isNotDocumentationNecessary: " + isNotDocumentationNecessary);
        _this.util().debug("hasNotCommercialFrequency: " + hasNotCommercialFrequency);

        return isNomAnce && !(hasValidPolarity && hasValidServiceRegime && hasValidFrequency && hasValidVoltage && hasValidEnclosureMaterial && hasValidPower && isNotDocumentationNecessary && hasNotCommercialFrequency);
    }

}