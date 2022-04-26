package _3342;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.filter.ObjectFilterDynamic;
import net.weg.eng.bean.rule.OperatorType;
import net.weg.eng.bean.object.ObjectHeader;

import java.util.List;

public class GenericRule1648815503755 {

    private static final GenericRule1648815503755 INSTANCE = new GenericRule1648815503755();

    public static GenericRule1648815503755 getInstance() {
        return GenericRule1648815503755.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);

        String tampaFunda = transformed.string("ZTAMPA_FUNDA_01");
        if ("".equals(tampaFunda)) tampaFunda = "00002";
        String aplicacao = transformed.string("ZAPLICACAO_MOTOR_01");

        ObjectFilterDynamic filter;

        filter = new ObjectFilterDynamic("LINK_QR_CODE_CHINA");
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "ZAPLICACAO_MOTOR_01", OperatorType.Equality, aplicacao);
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "ZCARCACA_COM_COMPLEMENTO_01", OperatorType.Equality, transformed.string("ZCARCACA_COM_COMPLEMENTO_01"));
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "ZMATERIAL_INVOLUCRO_01", OperatorType.Equality, transformed.string("ZMATERIAL_INVOLUCRO_01"));
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "ZTAMPA_FUNDA_01", OperatorType.Equality, tampaFunda);
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_PLANTA_CEL", OperatorType.Equality, _this.string("SIPWMO_PLANTA_CEL"));
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_POTENCIA_CEL", OperatorType.Equality, _this.string("SIPWMO_POTENCIA_CEL"));

        if(aplicacao.contains("00101")){
            filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_POLARIDADE_CEL", OperatorType.Equality, _this.string("SIPWMO_POLARIDADE_CEL"));
        }

        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_EFF_GRADE_CEL", OperatorType.Equality, _this.string("SIPWMO_EFF_GRADE_CEL"));
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_MODEL_CEL", OperatorType.Equality);
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_QR_CODE", OperatorType.Equality);
        filter.add("LINK_QR_CODE_CHINA", "LINK_QR_CODE_CHINA", "SIPWMO_RENDIMENTO_CEL", OperatorType.Equality);

        List < ObjectHeader > results = filter.filter().getResults();
        int size = results.size();

        if (size <= 0) {
            _this.set("ZNIVEL_EFIC_MOTOR_CHINA_01", "00005");
        } else {
            String qrCode = "http://elm.bbqk.com/index40.html?a=" + results.get(0).getObjectContext().get("SIPWMO_QR_CODE");
            String eff = results.get(0).getObjectContext().get("SIPWMO_RENDIMENTO_CEL") + "";
            String model = results.get(0).getObjectContext().get("SIPWMO_MODEL_CEL") + "";
            _this.set("SIPWMO_QR_CODE", qrCode);
            _this.set("SIPWMO_MODEL_CEL", model);
            _this.set("SIPWMO_RENDIMENTO_CEL", eff);
            _this.util().debug(transformed.string("ZAPLICACAO_MOTOR_01"));
            _this.util().debug(transformed.string("ZCARCACA_COM_COMPLEMENTO_01"));
            _this.util().debug(transformed.string("ZMATERIAL_INVOLUCRO_01"));
            _this.util().debug(tampaFunda);
            _this.util().debug(_this.string("SIPWMO_PLANTA_CEL"));
            _this.util().debug(_this.string("SIPWMO_POTENCIA_CEL"));
            _this.util().debug(_this.string("SIPWMO_POLARIDADE_CEL"));
            _this.util().debug(_this.getValueDescription("ZNIVEL_EFIC_MOTOR_CHINA_01") + "");

        }
    }
}