package _7996;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.ArrayList;
import java.util.List;

public class GenericRule1637657574083 {

    private static final GenericRule1637657574083 INSTANCE = new GenericRule1637657574083();

    public static GenericRule1637657574083 getInstance() {
        return GenericRule1637657574083.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ObjectContext variantConfiguration = _this.getRoot().getChildByAliasRecursive("sec_transformed");
        ObjectContext certificacaoMotor = _this.getRoot().getChildByAliasRecursive("certificacao_motor");
        ObjectContext certificacaoCompliance = _this.getRoot().getChildByAliasRecursive("certificacao_compliance");
        String certDesc = "";
        String lang = _this.util().getCurrentLanguage();
        String labelMens = _this.util().label("zvc_wmo_msg_00063", lang).toString();
        String labelMens2 = _this.util().label("zvc_wmo_msg_00113", lang).toString();
        String labelMens3 = _this.util().label("zvc_wmo_msg_00425", lang).toString();
        String labelRule = _this.util().label("rule", lang).toString();
        String certMens = "";
        String atendidosMsg = "";
        String grouperType = _this.string("ZTIPO_CONFIGURADOR_01");

        List certificadosVC = (List) variantConfiguration.get("ZCERTIFICACAO_MOTOR_01");
        List certificadosCM = null;

        if (certificacaoMotor != null) {
            certificadosCM = (List) certificacaoMotor.get("ZCERTIFICACAO_MOTOR_01");
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

                    }
                    i++;
                    if (!"".equals(certMens)) {
                        if (  "00001".equals(grouperType)) {
                            zconf.getMessages().error(" " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens + " " + certMens + ".");
                            zconf.getMessages().error("  " +_this.getCurrentRule().getRuleVersionId().toString(), labelMens2);
                        } else {
                            zconf.getMessages().alert(" " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens + " " + certMens + ".");
                            zconf.getMessages().alert("  " + _this.getCurrentRule().getRuleVersionId().toString(), labelMens2);
                        }

                    }
                }
            }

            if (!"".equals(certMens) && certificacaoCompliance != null) {
                List logs = (List) certificacaoCompliance.get("LOG_CERTIFICACAO_02");
                List < String > msgs = ProcessLog(logs, certificadosVC);
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

    public static List < String > ProcessLog(List logs, List certificadosVC) {
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
        }
        return certificadoTraduzido;
    }

}