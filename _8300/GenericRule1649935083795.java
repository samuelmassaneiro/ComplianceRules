package _3411;

import java.text.DecimalFormat;
import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.filter.ObjectFilterDynamic;
import net.weg.eng.bean.rule.OperatorType;
import net.weg.eng.bean.object.ObjectHeader;
import net.weg.eng.service.object.ObjectContext;

import java.util.stream.IntStream;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.*;
import java.lang.*;
import java.io.*;

import net.weg.eng.datatype.ObjectValueList;

public class GenericRule1649935083795 {

    private static final GenericRule1649935083795 INSTANCE = new GenericRule1649935083795();

    public static GenericRule1649935083795 getInstance() {
        return GenericRule1649935083795.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);
        ComponentBase projetoEletrico = _this.getCmp("../../../sec_dados_container->projeto_eletrico").getDecorated(ComponentBase.class);

        String codNorma = "";
        String nivelGBValidado = "None (Worse than GB3)";
        Integer rpmTabelado = 0;
        double auxMaiorEff = 0.0;
        double GB1RendInterpolA = 0.0;
        double GB1RendInterpolB = 0.0;
        double GB1RendInterpolFinal = 0.0;
        double GB2RendInterpolA = 0.0;
        double GB2RendInterpolB = 0.0;
        double GB2RendInterpolFinal = 0.0;
        double GB3RendInterpolA = 0.0;
        double GB3RendInterpolB = 0.0;
        double GB3RendInterpolFinal = 0.0;
        String rendGB1Interpolado = "";
        String rendGB2Interpolado = "";
        String rendGB3Interpolado = "";
        double aux = 0.0;
        double aux2 = 0.0;
        double aux3 = 0.0;
        double potAbaixo = 0.0;
        double potAcima = 0.0;
        DecimalFormat df = new DecimalFormat("0.0");

        String aplicacao = transformed.string("ZAPLICACAO_MOTOR_01");
        String velocidade = "";
        String[] valorVelocidade;

        String[] rpm;
        Integer rpmDouble;
        String[] rpmArray = {"",""};
        double menorRendimento = projetoEletrico.number("V_MENOR_RENDIMENTO_01");
        String[] potenciaMultiUnidade = projetoEletrico.getValueDescription("POTENCIA_KW_DESEMPENHO_01").toString().split("\\.");
        double potenciaMotor = Double.parseDouble(potenciaMultiUnidade[0]);
        if (potenciaMotor > 315.0) potenciaMotor = 315.0;

        try {
            velocidade = (String) transformed.getValueDescription("ZDETALHE1_01");
            rpm = velocidade.split(" ");
        } catch (Exception e) {
            velocidade = (String) transformed.getValueDescription("RPM_DESEMPENHO_01");
            rpm = velocidade.split(" ");
        }

        boolean btw500And1000 = Double.parseDouble(rpm[0]) > 500 && Double.parseDouble(rpm[0]) <1000;
        boolean btw1000And1500 = Double.parseDouble(rpm[0]) > 1000 && Double.parseDouble(rpm[0]) < 1500;
        boolean btw1500And2000 = Double.parseDouble(rpm[0]) > 1500 && Double.parseDouble(rpm[0]) < 2000;
        boolean btw2000And2500 = Double.parseDouble(rpm[0]) > 2000 && Double.parseDouble(rpm[0]) < 2500;
        boolean btw2500And3000 = Double.parseDouble(rpm[0]) > 2500 && Double.parseDouble(rpm[0]) < 3000;

        rpmDouble = Integer.parseInt(rpm[0]);

        if (rpmDouble > 500 && rpmDouble < 1000){
            rpmArray[0] = "500";
            rpmArray[1] = "1000";
        }
        else if (rpmDouble > 1000 && rpmDouble < 1500){
            rpmArray[0] = "1000";
            rpmArray[1] = "1500";
        }
        else if (rpmDouble > 1500 && rpmDouble < 2000){
            rpmArray[0] = "1500";
            rpmArray[1] = "2000";
        }
        else if (rpmDouble > 2000 && rpmDouble < 2500){
            rpmArray[0] = "2000";
            rpmArray[1] = "2500";
        }
        else if (rpmDouble > 2500 && rpmDouble < 3000){
            rpmArray[0] = "2500";
            rpmArray[1] = "3000";
        }
        else if (rpmDouble > 3000){
            rpmArray[0] = "3000";
        }
        else{
            rpmArray[0] = rpmDouble.toString();
        }

        if (rpmArray[1] == ""){
            rpmArray[1] = null;
            valorVelocidade = rpmArray;
        }
        else{
            valorVelocidade = rpmArray;
        }

        /*
        int rpmTable[] = {
            500,
            1000,
            1500,
            2000,
            2500,
            3000
        };

        int rpmMotor = Integer.parseInt(rpm[0]);
        rpmTabelado = IntStream.range(0, rpmTable.length).map(i -> rpmTable[rpmTable.length - 1 - i]).filter(n -> n < rpmMotor).findFirst().orElse(-1);
        _this.util().debug("ClosestRpm: "+ rpmTabelado);

        */

        String polos = transformed.string("YPOLARIDADE_COMPLETA");
        String potenciaLinkAbaixo = "";
        String potenciaLinkAcima = "";
        String model = "";
        boolean isWquattro = aplicacao.equals("00101");

        if(isWquattro) model = "WQUATTRO";
        else model = "MAGNET";

        double[] potenciasPadronizadas = {

                0.55,
                0.75,
                1.1,
                1.5,
                2.2,
                3.0,
                4.0,
                5.5,
                7.5,
                11.0,
                15.0,
                18.5,
                22.0,
                30.0,
                37.0,
                45.0,
                55.0,
                75.0,
                90.0,
                110.0,
                132.0,
                160.0,
                200.0,
                250.0,
                315.0,
                375.0,

        };

        for (double pot: potenciasPadronizadas) {
            if (potenciaMotor == pot) {
                potenciaLinkAbaixo = pot + "";
                break;
            } else if (potenciaMotor < pot) {
                potenciaLinkAcima = pot + "";
                potAcima = Double.parseDouble(potenciaLinkAcima);
                break;
            }
            potenciaLinkAbaixo = pot + "";
        }
        potAbaixo = Double.parseDouble(potenciaLinkAbaixo);

        _this.util().debug(potenciaLinkAbaixo);
        _this.util().debug(potenciaLinkAcima);

        ObjectFilterDynamic filter = new ObjectFilterDynamic("LINK_GB_CODE_CHINA");
        filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "POTENCIA_LINK_GB_"+model, OperatorType.Equality, potenciaLinkAbaixo);
        if (!"".equals(potenciaLinkAcima)){
            filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "POTENCIA_LINK_GB_"+model, OperatorType.Equality, potenciaLinkAcima);
        }
        if(isWquattro){
            filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "POLOS_LINK_GB_"+model, OperatorType.Equality, polos);
        }
        else {
            filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "VELOCIDADE_LINK_GB_"+model, OperatorType.Equality, valorVelocidade[0]);
        }
        if (!(valorVelocidade[1] == null)) {
            filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "VELOCIDADE_LINK_GB_"+model, OperatorType.Equality, valorVelocidade[1]);
        }
        filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "EFICIENCIA_LINK_GB_"+model, OperatorType.Equality);
        filter.add("LINK_GB_CODE_CHINA", "LINK_GB_CODE_CHINA", "NIVEL_LINK_GB_"+model, OperatorType.Equality);

        List < ObjectHeader > results = filter.filter().getResults();
        int size = results.size();
        _this.util().debug(size);
        _this.util().debug("rend motor " + menorRendimento);

        ArrayList<Double> arrayEffGb1 = new ArrayList<Double>();
        ArrayList<Double> arrayEffGb2 = new ArrayList<Double>();
        ArrayList<Double> arrayEffGb3 = new ArrayList<Double>();

        if(btw500And1000 || btw1000And1500 || btw1500And2000 || btw2000And2500 || btw2500And3000) {

            for (int i = 0; i < size; i++) {

                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB1")) {
                    arrayEffGb1.add(results.get(i).getObjectContext().getDecorated(ComponentBase.class).number("EFICIENCIA_LINK_GB_" + model));
                }
                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB2")) {
                    arrayEffGb2.add(results.get(i).getObjectContext().getDecorated(ComponentBase.class).number("EFICIENCIA_LINK_GB_" + model));
                }
                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB3")) {
                    arrayEffGb3.add(results.get(i).getObjectContext().getDecorated(ComponentBase.class).number("EFICIENCIA_LINK_GB_" + model));
                }

            }
            _this.util().debug("LIST GB1: " + arrayEffGb1);
            _this.util().debug("LIST GB2: " + arrayEffGb2);
            _this.util().debug("LIST GB3: " + arrayEffGb3);

            Double rpmDown = 0.0;
            Double rpmUp = 0.0;
            Double effGb1Down = Collections.min(arrayEffGb1);
            Double effGb1UP = Collections.max(arrayEffGb1);
            Double effGb2Down = Collections.min(arrayEffGb2);
            Double effGb2UP = Collections.max(arrayEffGb2);
            Double effGb3Down = Collections.min(arrayEffGb3);
            Double effGb3UP = Collections.max(arrayEffGb3);
            Double EffInterpolGb1 = 0.0;
            Double EffInterpolGb2 = 0.0;
            Double EffInterpolGb3 = 0.0;

            if(btw500And1000){
                rpmDown = 500.0;
                rpmUp = 1000.0;
            }
            if(btw1000And1500){
                rpmDown = 1000.0;
                rpmUp = 1500.0;
            }
            if(btw1500And2000){
                rpmDown = 1500.0;
                rpmUp = 2000.0;
            }
            if(btw2000And2500){
                rpmDown = 2000.0;
                rpmUp = 2500.0;
            }
            if(btw2500And3000){
                rpmDown = 2500.0;
                rpmUp = 3000.0;
            }

            //Interpolação Eff

            EffInterpolGb1 = (-(effGb1Down*rpmUp) + (Double.parseDouble(rpm[0])*effGb1Down) - (Double.parseDouble(rpm[0])*effGb1UP) + (rpmDown*effGb1UP))/(rpmDown-rpmUp);
            EffInterpolGb2 = (-(effGb2Down*rpmUp) + (Double.parseDouble(rpm[0])*effGb2Down) - (Double.parseDouble(rpm[0])*effGb2UP) + (rpmDown*effGb2UP))/(rpmDown-rpmUp);
            EffInterpolGb3 = (-(effGb3Down*rpmUp) + (Double.parseDouble(rpm[0])*effGb3Down) - (Double.parseDouble(rpm[0])*effGb3UP) + (rpmDown*effGb3UP))/(rpmDown-rpmUp);

            for(int i = 0; i < size; i++){

                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB1")) {
                    results.get(i).getObjectContext().getDecorated(ComponentBase.class).set("EFICIENCIA_LINK_GB_"+model, EffInterpolGb1);
                }
                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB2")) {
                    results.get(i).getObjectContext().getDecorated(ComponentBase.class).set("EFICIENCIA_LINK_GB_"+model, EffInterpolGb2);
                }
                if (results.get(i).getObjectContext().get("NIVEL_LINK_GB_" + model).toString().equals("GB3")) {
                    results.get(i).getObjectContext().getDecorated(ComponentBase.class).set("EFICIENCIA_LINK_GB_"+model, EffInterpolGb3);
                }
            }
            _this.util().debug("GB1: " + EffInterpolGb1);
            _this.util().debug("GB2: " + EffInterpolGb2);
            _this.util().debug("GB3: " + EffInterpolGb3);
        }
        if (size > 0) {
            if(size == 6){
            }
            if ("".equals(potenciaLinkAcima)) {
                for (int i = 0; i < size; i++) {
                    double effRendLink = results.get(i).getObjectContext().getDecorated(ComponentBase.class).number("EFICIENCIA_LINK_GB_"+model);
                    String nvRendLink = results.get(i).getObjectContext().get("NIVEL_LINK_GB_"+model) + "";
                    String potLink = results.get(i).getObjectContext().get("POTENCIA_LINK_GB_"+model) + "";

                    _this.util().debug("pot " + potLink + " " + effRendLink + " - " + nvRendLink);
                    _this.getMessages().alert("gbs" + i, "pot " + potLink + "| eff min: " + effRendLink + "% para ser " + nvRendLink);
                    if (menorRendimento >= effRendLink && effRendLink >= auxMaiorEff) {
                        auxMaiorEff = effRendLink;
                        nivelGBValidado = nvRendLink;
                        _this.set("ZEFF_GB_CEL038", effRendLink);
                        _this.util().debug("ZEFF_GB_CEL038: " + _this.getValueDescription("ZEFF_GB_CEL038"));
                    }
                }
                _this.util().debug("ql gb? " + nivelGBValidado);
            } else {

                for (int i = 0; i < size; i++) {
                    double effRendLink = results.get(i).getObjectContext().getDecorated(ComponentBase.class).number("EFICIENCIA_LINK_GB_"+model);
                    String nvRendLink = results.get(i).getObjectContext().get("NIVEL_LINK_GB_"+model) + "";
                    String potLink = results.get(i).getObjectContext().get("POTENCIA_LINK_GB_"+model) + "";
                    _this.util().debug("pot " + potLink + " " + effRendLink + " - " + nvRendLink);

                    switch (nvRendLink) {
                        case "GB1":
                            if (GB1RendInterpolA == 0.0) GB1RendInterpolA = effRendLink;
                            else GB1RendInterpolB = effRendLink;
                            if (GB1RendInterpolA > GB1RendInterpolB && GB1RendInterpolB != 0) {
                                GB1RendInterpolB = GB1RendInterpolA;
                                GB1RendInterpolA = effRendLink;
                            }
                            if (GB1RendInterpolA > 0.0 && GB1RendInterpolB > 0.0) {
                                aux = (potenciaMotor - potAbaixo) / (potAcima - potenciaMotor);
                                _this.util().debug("aux: " + aux);
                                aux2 = (aux * GB1RendInterpolB) + GB1RendInterpolA;
                                aux3 = aux + 1;
                                GB1RendInterpolFinal = aux2 / aux3;
                                rendGB1Interpolado = df.format(GB1RendInterpolFinal);
                                _this.util().debug("GB1RendInterpolFinal: " + GB1RendInterpolFinal);
                                _this.util().debug("arredondado: " + rendGB1Interpolado);
                                _this.getMessages().alert("GB1interpol", "GB1RendInterpolFinal: " + GB1RendInterpolFinal + "| arredondado: " + rendGB1Interpolado);
                                GB1RendInterpolFinal = Double.parseDouble(rendGB1Interpolado.replace(",","."));
                                _this.set("ZEFF_GB_CEL038", GB1RendInterpolA);
                                _this.util().debug("ZEFF_GB_CEL038: " + _this.getValueDescription("ZEFF_GB_CEL038"));
                            }
                            break;
                        case "GB2":
                            if (GB2RendInterpolA == 0.0) GB2RendInterpolA = effRendLink;
                            else GB2RendInterpolB = effRendLink;
                            if (GB2RendInterpolA > GB2RendInterpolB && GB2RendInterpolB != 0) {
                                GB2RendInterpolB = GB2RendInterpolA;
                                GB2RendInterpolA = effRendLink;
                            }
                            if (GB2RendInterpolA > 0.0 && GB2RendInterpolB > 0.0) {
                                aux = (potenciaMotor - potAbaixo) / (potAcima - potenciaMotor);
                                aux2 = (aux * GB2RendInterpolB) + GB2RendInterpolA;
                                aux3 = aux + 1;
                                GB2RendInterpolFinal = aux2 / aux3;
                                rendGB2Interpolado = df.format(GB2RendInterpolFinal);
                                _this.util().debug("GB2RendInterpolFinal: " + GB2RendInterpolFinal);
                                _this.util().debug("arredondado: " + rendGB2Interpolado);
                                _this.getMessages().alert("GB2interpol", "GB2RendInterpolFinal: " + GB2RendInterpolFinal + "| arredondado: " + rendGB2Interpolado);
                                GB2RendInterpolFinal = Double.parseDouble(rendGB2Interpolado.replace(",","."));
                                _this.set("ZEFF_GB_CEL038", GB2RendInterpolA);
                                _this.util().debug("ZEFF_GB_CEL038: " + _this.getValueDescription("ZEFF_GB_CEL038"));
                            }
                            break;
                        case "GB3":
                            if (GB3RendInterpolA == 0.0) GB3RendInterpolA = effRendLink;
                            else GB3RendInterpolB = effRendLink;
                            if (GB3RendInterpolA > GB3RendInterpolB && GB3RendInterpolB != 0) {
                                GB3RendInterpolB = GB3RendInterpolA;
                                GB3RendInterpolA = effRendLink;
                            }
                            if (GB3RendInterpolA > 0.0 && GB3RendInterpolB > 0.0) {
                                aux = (potenciaMotor - potAbaixo) / (potAcima - potenciaMotor);
                                aux2 = (aux * GB3RendInterpolB) + GB3RendInterpolA;
                                aux3 = aux + 1;
                                GB3RendInterpolFinal = aux2 / aux3;
                                rendGB3Interpolado = df.format(GB3RendInterpolFinal);
                                _this.util().debug("GB3RendInterpolFinal: " + GB3RendInterpolFinal);
                                _this.util().debug("arredondado: " + rendGB3Interpolado);
                                _this.getMessages().alert("GB3interpol", "GB3RendInterpolFinal: " + GB3RendInterpolFinal + "| arredondado: " + rendGB3Interpolado);
                                GB3RendInterpolFinal = Double.parseDouble(rendGB3Interpolado.replace(",","."));
                                _this.set("ZEFF_GB_CEL038", GB3RendInterpolA);
                                _this.util().debug("ZEFF_GB_CEL038: " + _this.getValueDescription("ZEFF_GB_CEL038"));
                            }
                            break;
                    }

                }

                if (menorRendimento >= GB3RendInterpolFinal) nivelGBValidado = "GB3";
                if (menorRendimento >= GB2RendInterpolFinal) nivelGBValidado = "GB2";
                if (menorRendimento >= GB1RendInterpolFinal) nivelGBValidado = "GB1";

                _this.util().debug("GB atendido: " + nivelGBValidado);
            }
        }

        _this.getMessages().alert("debugao", "Rendimento motor: " + menorRendimento + "| Nivel GB atendido: " + nivelGBValidado);

        String gb = "";
        String ef = "";
        switch (nivelGBValidado) {
            case "GB3":
                gb = "00002";
                ef = "3";
                break;
            case "GB2":
                gb = "00001";
                ef = "2";
                break;
            case "GB1":
                gb = "00003";
                ef = "1";
                break;
            default:
                gb = "00004";
                ef = "0";
        }
        _this.set("ZNIVEL_EFIC_MOTOR_CHINA_01",gb);
        _this.set("SIPWMO_EFF_GRADE_CEL",ef);
        _this.util().debug("Magnet ou Wquattro: " + model);
        _this.util().debug("NIVEL GB: " + gb);
        _this.util().debug("NIVEL EFF: " + ef);
    }
}