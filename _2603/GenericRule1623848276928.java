package _2603;

import net.weg.eng.service.object.ComponentBase;
import java.util.*;
import java.util.regex.*;

public class GenericRule1623848276928 {

    private static final GenericRule1623848276928 INSTANCE = new GenericRule1623848276928();

    public static GenericRule1623848276928 getInstance() {
        return GenericRule1623848276928.INSTANCE;
    }
    public void execute(ComponentBase _this) {
        ComponentBase vc;
        ComponentBase cel;
        String node = "";

        if (_this.getCmp("SIPWMO") == null) {
            vc = _this;
            cel = _this;
        } else if ("00002".equals(_this.getCmp("SIPWMO").string("SIPWMO_DEFINE_PROCESSO_GERACAO"))) { //Processo novo
            vc = _this;
            cel = _this;
        } else {
            cel = _this.getCmp("SIPWMO->etiq_eff_china");
            vc = _this.getCmp("SIPWMO->variantConfiguration");
        }

        List < String > debug = new ArrayList < String > ();
        debug.add("Entra no escopo");

        //ZCATEGORIA_01*********************************************************
        //Categoria N
        boolean entNav = "00010".equals(_this.string("ZORGANISMO_CERTIFICA_NAVAL_01")) || "".equals(_this.string("ZORGANISMO_CERTIFICA_NAVAL_01"));
        if (!entNav) debug.add("Fora do escopo -Naval");

        //ZCATEGORIA_01*********************************************************
        //Categoria N
        boolean entCat = "00006".equals(_this.string("ZCATEGORIA_01"));
        if (!entCat) debug.add("Fora do escopo -Categoria");

        //ZFASE_01**************************************************************
        //Trifasico
        boolean entFas = "00012".equals(_this.string("ZFASE_01"));
        if (!entFas) debug.add("Fora do escopo -Fase");

        //ZINVOLUCRO_01*********************************************************
        //Fechado
        boolean entInv = "00003".equals(_this.string("ZINVOLUCRO_01"));
        if (!entInv) debug.add("Fora do escopo -Involucro aberto");

        //ZTIPO_POLARIDADE_01***************************************************
        //Polaridade unica
        boolean entTpPol = "00002".equals(_this.string("ZTIPO_POLARIDADE_01"));
        if (!entTpPol) debug.add("Fora do escopo -Tipo polaridade");

        //ZGRUPO_CARCACA_01*****************************************************
        //80 a 355
        boolean entCarc =
                        "00074".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00076".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00020".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00022".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00024".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00027".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00029".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00031".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00036".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00039".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00041".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00045".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00047".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00049".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00051".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00398".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00425".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00426".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00429".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00430".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00431".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00464".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00465".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00466".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00467".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00468".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00471".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00507".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00508".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00327".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00328".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00440".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00441".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00442".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00481".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00483".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00480".equals(_this.string("ZGRUPO_CARCACA_01")) ||
                        "00473".equals(_this.string("ZGRUPO_CARCACA_01"));
        if (!entCarc) debug.add("Fora do escopo -Carcaça");

        //ZREFRIGERACAO_01******************************************************
        //TEFC ou TFVE
        boolean entRef =
                "00020".equals(_this.string("ZREFRIGERACAO_01")) ||
                        "00025".equals(_this.string("ZREFRIGERACAO_01")) ||
                        "00048".equals(_this.string("ZREFRIGERACAO_01")) ||
                        "00052".equals(_this.string("ZREFRIGERACAO_01"));
        if (!entRef) debug.add("Fora do escopo -Refrigeracao");

        //ZPOLARIDADE_COMPLETA_01***********************************************
        //2,4,6 ou 8 poloes
        boolean entPol =
                "00001".equals(_this.string("ZPOLARIDADE_COMPLETA_01")) ||
                        "00003".equals(_this.string("ZPOLARIDADE_COMPLETA_01")) ||
                        "00009".equals(_this.string("ZPOLARIDADE_COMPLETA_01")) ||
                        "00016".equals(_this.string("ZPOLARIDADE_COMPLETA_01"));
        if (!entPol) debug.add("Fora do escopo -Polos");

        //ZDETALHE1_01**********************************************************
        //Smoke maior que 250grausC
        boolean entSmoke =
                "00026".equals(_this.string("ZDETALHE1_01")) ||
                        "00027".equals(_this.string("ZDETALHE1_01")) ||
                        "00036".equals(_this.string("ZDETALHE1_01")) ||
                        "00044".equals(_this.string("ZDETALHE1_01")) ||
                        "00071".equals(_this.string("ZDETALHE1_01")) ||
                        "00025".equals(_this.string("ZDETALHE1_01")) ||
                        "00024".equals(_this.string("ZDETALHE1_01")) ||
                        "00052".equals(_this.string("ZDETALHE1_01")) ||
                        "00043".equals(_this.string("ZDETALHE1_01")) ||
                        "00038".equals(_this.string("ZDETALHE1_01"));
        if (entSmoke) debug.add("Fora do escopo -Smoke 250graus+");

        //ZREGIME_SERVICO_01****************************************************
        //Regime S1 ou S3 >= 80
        double cicloS3 = _this.number("ZFATOR_DURACAO_CICLO_S3_01");
        String regime = _this.string("ZREGIME_SERVICO_01");
        boolean isS3ciclo80 = "00006".equals(regime) && cicloS3 >= 80.0;
        boolean isS1 = "00003".equals(regime);
        boolean entReg = isS1 || isS3ciclo80;
        if (!entReg) debug.add("Fora do escopo -Regime de servico");

        //ZALTITUDE_01**********************************************************
        //Altitude ate 1000m
        String sAlt = _this.util().desc("ZALTITUDE_01") + "";
        Double dAlt = 0.0;
        sAlt = sAlt.replaceAll("m", "");
        sAlt = sAlt.replaceAll(" ", "");
        try {
            dAlt = Double.parseDouble(sAlt);
        } catch (Exception e) {}
        boolean entAlt = dAlt != 0.0 && dAlt <= 1000.0;
        if (!entAlt) debug.add("Fora do escopo -Altitude");

        //ZFREQUENCIA_COMERCIAL_01**********************************************
        //Necessario ao menos uma frequencia em 50hz
        boolean entFreq = false;
        String freq = _this.get("ZFREQUENCIA_01").toString();
        String tensaoSemCabo = _this.getValueDescription("ZTENSAO_SEM_CABO_01")+"";
        String freqCom = "";
        try {
            freqCom = _this.get("ZFREQUENCIA_COMERCIAL_01").toString();
        } catch (Exception e) {}
        entFreq = freq.equals("00064") || freqCom.equals("00069") || freqCom.equals("00081") || tensaoSemCabo.contains("//");

        // boolean possuiFreq50 = false;
        // double tensao50 = _this.number("V_MENOR_TENSAO_50HZ_01");
        // entFreq = tensao50 > 0.0;

        if (!entFreq) {
            debug.add("Fora do escopo -Frequencia");
        }

        //ZTEMPERATURA_AMBIENTE_MAX_01******************************************
        double ambMax = _this.number("ZTEMPERATURA_AMBIENTE_MAX_01");
        boolean EntTMax = ambMax >= -15.0;
        if (!EntTMax) debug.add("Fora do escopo -T.amb Max");

        //ZTEMPERATURA_AMBIENTE_MIN_01******************************************
        double ambMin = _this.number("ZTEMPERATURA_AMBIENTE_MIN_01");
        boolean entTMin = ambMin <= 40.0;
        if (!entTMin) debug.add("Fora do escopo -T.amb Min");

        //ZPOTENCIA_ESTATISTICA_CV_01*******************************************
        boolean entPot = false;
        double pot = _this.number("ZPOTENCIA_ESTATISTICA_CV_01");
        entPot = pot >= 0.16 && pot <= 1360.0;
        if (!entPot && pot != 0.0) debug.add("Fora do escopo -Potencia");

        //V_MAIOR_TENSAO_01*****************************************************
        double tensao = _this.number("V_MAIOR_TENSAO_01");
        boolean entTen = tensao <= 1000.0;
        if (!entTen) debug.add("Fora do escopo -tensao");

        //ZVCWMO_MODEL_CEL******************************************************
        //Cria model CEL
        String modelCEL = "";

        String linha = _this.getValueDescription("ZAPLICACAO_MOTOR_01") + "";
        String carc = _this.getValueDescription("ZCARCACA_COM_COMPLEMENTO_01") + "";
        String polos = _this.getValueDescription("ZPOLARIDADE_COMPLETA_01") + "";
        String potKW = _this.getValueDescription("V_MAIOR_POTENCIA_KW_50HZ_01") + "";
        potKW = potKW.split("\\.0")[0];
        String IEcode = _this.getValueDescription("V_MENOR_NIVEL_RENDIMENTO_IEC_50HZ_01") + "";

        modelCEL = linha + "-" + carc + "-" + polos + " (" + potKW + "kW " + IEcode + ")";


        //result****************************************************************
        boolean entraEscopo =
                entAlt && entFreq && EntTMax && entTMin && entPot && entTen &&
                        entCat && entFas && entTpPol && entRef && entPol && entReg &&
                        entInv && entNav && !entSmoke;

        for(String x : debug){
            _this.util().debug("872410 "+x);
        }
        _this.util().debug("872410 - Chegou antes do IF. Entra escopo-> " + entraEscopo);
        if (entraEscopo) {
            if ((pot >= 0.75 && pot <= 375.0) && entCarc) {
                //Avalia eficiência e necessita etiqueta CEL
                try {
                    _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ATENDER_EF_CEL_01", "00001");
                    _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ETIQUETA_CEL_01", "00001");
                } catch(Exception e) {
                }
                //vc.set("ZVCWMO_MODEL_CEL", modelCEL);
            } else if ((pot < 0.75 || pot > 375.0) || !entCarc) {
                debug.remove(0);
                //Avalia eficiência e não necessita etiqueta CEL
                try {
                    _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ATENDER_EF_CEL_01", "00001");
                    _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ETIQUETA_CEL_01", "00002");
                } catch(Exception e) {
                }
            }
            //vc.set("CMWMO_ESCOPO_CEL_2021", debug);
            //executa grupo para avaliar nivel GB
            _this.util().executeRuleGroup(3411);
        } else {
            debug.remove(0);
            //vc.set("CMWMO_ESCOPO_CEL_2021", debug);
            //Não avalia eficiência e não necessita etiqueta CEL
            try {
                _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ATENDER_EF_CEL_01", "00002");
                _this.getCmp("RULE_SERVICE_VC_10000008->zvcwmo_messages").set("ZNECESSITA_ETIQUETA_CEL_01", "00002");
            } catch(Exception e) {
            }
        }
    }
}