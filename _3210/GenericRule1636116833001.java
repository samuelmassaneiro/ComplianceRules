package _3210;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1636116833001 {

    private static final GenericRule1636116833001 INSTANCE = new GenericRule1636116833001();

    public static GenericRule1636116833001 getInstance() {
        return GenericRule1636116833001.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        double A = 0;
        double B = 0;
        double C = 0;
        double D = 0;
        double E = 0;
        A = Double.parseDouble(_this.get("VAR_TEMP_01").toString());
        B = Double.parseDouble(_this.get("VAR_TEMP_02").toString());
        C = Double.parseDouble(_this.get("VAR_TEMP_03").toString());
        D = Double.parseDouble(_this.get("VAR_TEMP_04").toString());
        E = Double.parseDouble(_this.get("VAR_TEMP_05").toString());
        _this.util().debug("A*(B+C+D+E) = " + A + "*(" + B + "+" + C + "+" + D + "+" + E + ") = " + A*(B+C+D+E));
        double ret = A*(B+C+D+E);
        return (ret > 0);
    }
}

/*
Condição Geral: A
Alimentação: Trifásico, monofásico;
Tensão: 50 A 1000 Vca;
VAR_TEMP_01 = 1
--------------------------------------------------------------------------------------------------------------------------------------------------
1ª Situação: B
Nível rendimento igual ou maior que IE3. (avaliando condição de 50Hz).
-Frequência: 50Hz, 60/50Hz ou derate em 50 Hz.
-Polaridade: 2 a 6p;
-Temperatura Ambiente Máxima: Até 60°C;
-Regime Serviço: S1;
-Altitude: Até 4000m;
-Potência de 0.75 a 375 KW;

Exceção: Mercado Europa, o nível de rendimento mínimo é o IE2, pois estes motores possuem etiqueta especificando o uso do motor apenas para
conversores de frequência.

VAR_TEMP_02 = 1
--------------------------------------------------------------------------------------------------------------------------------------------------
2ª Situação: C
Nível rendimento igual ou maior que IE1. (avaliando condição de 50Hz).
-Potência de 0.12 a 0.74 KW

VAR_TEMP_03 = 1
--------------------------------------------------------------------------------------------------------------------------------------------------
3ª Situação: D
Nível rendimento igual ou maior que IE1. (avaliando condição de 50Hz).
-Altitude: Acima de 4000 m; ou
-Temperatura ambiente máxima: Acima de 60 °C;  ou
-Temperatura ambiente mínima: Abaixo de -30 °C; ou
-Polaridade: 8 polos; ou
-Refrigeração: TEAO ou TENV; ou
-Refime de serviço: Diferente de S1; ou
-Motofreio; ou
-Europa 60Hz; ou
-Marine motor.

VAR_TEMP_04 = 1
--------------------------------------------------------------------------------------------------------------------------------------------------
4ª Situação: E
Sem exigência para nível de rendimento mínimo.
-Polaridade: Acima de 8p ou Dupla polaridade
-Monofásico
-Frequência <> 50 ou 60 Hz

VAR_TEMP_05 = 1
--------------------------------------------------------------------------------------------------------------------------------------------------
MARCAÇÃO CE = A * (B + C + D + E)
*/