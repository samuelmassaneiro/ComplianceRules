package _3411;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1623956194236 {

    private static final GenericRule1623956194236 INSTANCE = new GenericRule1623956194236();

    public static GenericRule1623956194236 getInstance() {
        return GenericRule1623956194236.INSTANCE;
    }

    public void execute(ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);

        String planta = "";
        String cliente = "";
        String centro = "";
        String verificacaoVc = "";
        String polaridade = "";
        String potencia = "";
        verificacaoVc = transformed.string("ZVERIFICACAO_VC_WMO_01");

        if (!verificacaoVc.equals("")) {
            switch (verificacaoVc) {
                case "00002":
                    centro = "1100";
                    break;
                case "00003":
                    centro = "6100";
                    break;
                case "00004":
                    centro = "6101";
                    break;
                case "00005":
                    centro = "4100";
                    break;
            }
        }
        if (centro.equals("")) {
            centro = transformed.string("CENTRO_PRODUCAO");
        }

        cliente = transformed.string("ZPLACA_PRINCIPAL_CLIENTE_01");

        if (cliente.equals("00177")) {
            planta = "KSB";
        } else {
            switch (centro) {
                case "1100":
                    planta = "WMO";
                    break;
                case "6100":
                    planta = "WNT";
                    break;
                case "6101":
                    planta = "WRG";
                    break;
                case "4100":
                case "4102":
                    planta = "WPT";
                    break;
                default:
                    planta = "UNKNOWN";
                    _this.getRoot().getMessages().alert("1448701", _this.getTranslate("msg_cel_centro"));
            }
        }
        _this.set("SIPWMO_PLANTA_CEL", planta);

        polaridade = transformed.string("YPOLARIDADE_COMPLETA");
        _this.set("SIPWMO_POLARIDADE_CEL", polaridade);

        potencia = transformed.string("V_MAIOR_POTENCIA_KW_50HZ_01");
        _this.set("SIPWMO_POTENCIA_CEL", potencia);

    }
}