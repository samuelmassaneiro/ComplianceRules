package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.ArrayList;
import java.util.List;

public class GenericRule1637749625306 {

    private static final GenericRule1637749625306 INSTANCE = new GenericRule1637749625306();

    public static GenericRule1637749625306 getInstance() {
        return GenericRule1637749625306.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ComponentBase variantConfiguration = _this.getCmp("ZCONF_COMPLIANCE->sec_dados_container->sec_transformed");
        ObjectContext certificacaoMotor = _this.getRoot().getChildByAliasRecursive("certificacao_motor");
        ComponentBase certificacaoCompliance = _this.getCmp("ZCONF_COMPLIANCE->sec_certificacao_container->certificacao_compliance");
        String certDesc = "";
        String lang = _this.util().getCurrentLanguage();
        String labelMens = _this.util().label("zvc_wmo_msg_00063", lang).toString();
        String labelMens2 = _this.util().label("zvc_wmo_msg_00113", lang).toString();
        String labelMens3 = _this.util().label("zvc_wmo_msg_00425", lang).toString();
        String labelRule = _this.util().label("rule", lang).toString();
        String certMens = "";
        String atendidosMsg = "";
        String grouperType = variantConfiguration.string("ZTIPO_CONFIGURADOR_01");
        String msgAdicional = "";
        if (certificacaoCompliance.util().desc("COMPLIANCE_INFO_ADICIONAL", lang) != null){
            msgAdicional = certificacaoCompliance.util().desc("COMPLIANCE_INFO_ADICIONAL", lang);
        }

        List certificadosVC = (List) variantConfiguration.get("ZCERTIFICACAO_MOTOR_01");
        List certificadosCM = null;

        if (certificacaoMotor != null && certificacaoCompliance != null) {
            certificadosCM = (List) certificacaoCompliance.get("COMPLIANCE_CERTIFICADOS_ATENDIDOS");
            ObjectContext zconf = certificacaoMotor.getObjectContextParent().getObjectContextParent();
            certificacaoMotor.getNodeHash();
            int i = 0;
            List certDescription = (List) variantConfiguration.getValueDescription("ZCERTIFICACAO_MOTOR_01");
            for (Object certific: certificadosVC) {
                if(certific != null) {
                    _this.util().debug("certific " + certific);

                    if (certDescription.get(i) != null) {
                        certDesc = certDescription.get(i).toString();
                    }
                    _this.util().debug("certDesc " + i + ": " + certDesc);
                    if (!certificadosCM.contains(certific)) {
                        _this.util().debug("certDesc não atende " + certific + " - " + certDesc);
                        if (certMens.equals("")) {
                            certMens = certDesc;
                        } else {
                            certMens = certMens + ", " + certDesc;
                        }
                    } else {
                        if (atendidosMsg.equals("")) {
                            atendidosMsg = certDesc;
                        } else {
                            atendidosMsg = atendidosMsg + ", " + certDesc;
                        }
                        zconf.getMessages().alert(_this.getCurrentRule().getRuleVersionId().toString(), labelMens3 + " " + atendidosMsg + msgAdicional);
                    }
                    i++;
                    if (!"".equals(certMens)) {
                        if (  "00001".equals(grouperType)) {
                            zconf.getMessages().error(" " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens + " " + certMens + ".");
                            zconf.getMessages().error("  " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens2);
                        } else {
                            zconf.getMessages().alert("   " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens + " " + certMens + ".");
                            zconf.getMessages().alert("    " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens2);
                        }

                    }
                }
            }

            if (!"".equals(certMens)) {
                List logs = (List) certificacaoCompliance.get("LOG_CERTIFICACAO_02");
                List < String > msgs = ProcessLog(logs, certificadosVC, variantConfiguration);
                int imsg = 0;
                for (String msg: msgs) {
                    _this.util().debug("msg " + msg);
                    if (  "00001".equals(grouperType)) {
                        zconf.getMessages().error(msg.substring(2,9).replace("]",""), msg.substring(10));
                    } else {
                        zconf.getMessages().alert(msg.substring(2,9).replace("]",""), msg.substring(10));
                    }
                    imsg++;
                }
            }
        }
    }

    public static List < String > ProcessLog(List logs, List certificadosVC, ObjectContext variantConfiguration) {
        List < String > msg = new ArrayList< String >();
        for (Object log: logs) {
            String regex = "->";
            if (log != null) {
                if(log.toString().contains("#")){
                    regex = "#";
                }
                String[] logArray = log.toString().split(regex);
                String certificacaoLOG = logArray[0].replaceAll("\\s+$", "");
                String rule = "";
                String characteristic = "";
                String message = "";
                String certificadoTraduzido = "";
                if ("#".equals(regex)){
                    rule = logArray[1];
                    characteristic = logArray[2];
                    if (logArray.length > 3){
                        message = logArray[3];
                    }
                } else {
                    characteristic = logArray[1];
                    message = logArray[2];
                }
                certificadoTraduzido = traduzirCertificado(certificacaoLOG);
                if (certificadosVC.contains(certificadoTraduzido)){
                    if(variantConfiguration.get(characteristic) != null){
                        characteristic = variantConfiguration.getCharacteristicDescription(characteristic);
                    } else{
                        String[] characteristics = characteristic.split(":");
                        characteristic = characteristics[0];
                        if(variantConfiguration.get(characteristic) != null){
                            characteristic = variantConfiguration.getCharacteristicDescription(characteristic);
                        }
                    }
                    msg.add(" [" + rule + "] " + certificacaoLOG + " - " + characteristic + ": " + message);
                }
            }
        }
        return msg;
    }

    public static String traduzirCertificado(String certificado) {
        String certificadoTraduzido = "";

        switch (certificado) {
            case "CSA CLASS":
                certificadoTraduzido = "00001";
                break;
            case "CSA SEGURA":
                certificadoTraduzido = "00002";
                break;
            case "UL":
                certificadoTraduzido = "00003";
                break;
            case "UL SEGURA":
                certificadoTraduzido = "00004";
                break;
            case "UL FIRE PUMP":
                certificadoTraduzido = "00005";
                break;
            case "NOM-ANCE":
                certificadoTraduzido = "00006";
                break;
            case "CE":
                certificadoTraduzido = "00007";
                break;
            case "ATEX":
                certificadoTraduzido = "00008";
                break;
            case "IECEX":
                certificadoTraduzido = "00009";
                break;
            case "TUV":
                certificadoTraduzido = "00010";
                break;
            case "GOST":
                certificadoTraduzido = "00011";
                break;
            case "RU":
                certificadoTraduzido = "00012";
                break;
            case "EAC":
                certificadoTraduzido = "00013";
                break;
            case "CCC":
                certificadoTraduzido = "00015";
                break;
            case "SABS":
                certificadoTraduzido = "00016";
                break;
            case "CNEX":
                certificadoTraduzido = "00017";
                break;
            case "INMETRO":
                certificadoTraduzido = "00018";
                break;
            case "ECASEX":
                certificadoTraduzido = "00019";
                break;
            case "PSE":
                certificadoTraduzido = "00020";
                break;
            case "BV":
                certificadoTraduzido = "00022";
                break;
            case "MASC":
                certificadoTraduzido = "00021";
                break;
            case "ANZEX":
                certificadoTraduzido = "00023";
                break;
            case "CCOE":
                certificadoTraduzido = "00024";
                break;
            case "RINA":
                certificadoTraduzido = "00025";
                break;
            case "IRAM":
                certificadoTraduzido = "00026";
                break;
            case "CCCEX":
                certificadoTraduzido = "00027";
                break;
            case "CE 2021":
                certificadoTraduzido = "33333";
                break;
            case "UKCA":
                certificadoTraduzido = "00033";
                break;
        }
        return certificadoTraduzido;
    }

}