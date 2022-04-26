package _67;

import net.weg.eng.service.object.ComponentBase;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class GenericRule1603969862753 {

    private static final GenericRule1603969862753 INSTANCE = new GenericRule1603969862753();

    public static GenericRule1603969862753 getInstance() {
        return GenericRule1603969862753.INSTANCE;
    }
    @SuppressWarnings("unchecked")
    public void execute(final ComponentBase _this) {
        List < Double > effLevel = new ArrayList < Double > ();
        List < Double > table_A = new ArrayList < Double > ();
        List < Double > table_B = new ArrayList < Double > ();
        List < Double > table_C = new ArrayList < Double > ();
        List < Double > table_D = new ArrayList < Double > ();
        table_A.clear();
        table_B.clear();
        table_C.clear();
        table_D.clear();
        String i = null;
        String market = _this.string("ZMERCADO_01");
        String detail_2 = _this.string("ZDETALHE2_01");
        int crit_group = 1978;
        if (detail_2.equals("00022")) crit_group = 1363;


        _this.set("V_ENTIDADE_NORMA_VC_01", _this.get("ZENTIDADE_NORMA_01"));
        _this.set("V_MERCADO_VC_01", _this.get("ZMERCADO_01"));
        String norm = _this.string("ZENTIDADE_NORMA_01");
        for (int j = 1; j <= 10; j++) {
            if (j < 10) i = "0" + j;
            else i = j + "";
            if (_this.get("FREQUENCIA_DESEMPENHO_" + i) != null) {
                Double powerkw = _this.number("POTENCIA_KW_DESEMPENHO_" + i);
                Double efficiency = _this.number("RENDIMENTO_100_DESEMPENHO_" + i);
                _this.set("V_REND_DESEMPENHO_AGRUPADO_01", null);
                _this.set("V_POT_CV_DESEMPENHO_AGRUP_01", null);
                _this.set("V_FREQ_DESEMPENHO_AGRUPADA_01", null);
                _this.set("ZNIVEL_REND_DESEMPENHO_01", null);
                _this.set("V_POT_KW_DESEMPENHO_AGRUP_01", null);
                _this.set("ZNIVEL_REND_01_DESEMPENHO_" + i, null);
                _this.set("ZNIVEL_REND_02_DESEMPENHO_" + i, null);
                _this.set("V_REND_DESEMPENHO_AGRUPADO_01", _this.get("RENDIMENTO_100_DESEMPENHO_" + i));
                _this.set("V_POT_CV_DESEMPENHO_AGRUP_01", _this.get("POTENCIA_CV_DESEMPENHO_" + i));
                _this.set("V_FREQ_DESEMPENHO_AGRUPADA_01", _this.get("FREQUENCIA_DESEMPENHO_" + i));
                _this.set("V_POT_KW_DESEMPENHO_AGRUP_01", _this.get("POTENCIA_KW_DESEMPENHO_" + i));
                if (!market.equals("00009") && !market.equals("00012") && norm.equals("00006") && !detail_2.equals("00010") && !detail_2.equals("00013") && !detail_2.equals("00012") && !detail_2.equals("00032")) _this.set("ZENTIDADE_NORMA_01", "00004");
                _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                if (_this.get("ZNIVEL_REND_DESEMPENHO_01") != null) _this.set("ZNIVEL_REND_01_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));

                if (_this.get("ZENTIDADE_NORMA_01").equals("00006")) _this.set("ZENTIDADE_NORMA_01", "00004");
                else _this.set("ZENTIDADE_NORMA_01", "00006");

                _this.set("ZNIVEL_REND_DESEMPENHO_01", null);
                _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                if (_this.get("ZNIVEL_REND_DESEMPENHO_01") != null) _this.set("ZNIVEL_REND_02_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                if (_this.get("V_ENTIDADE_NORMA_VC_01") != null) _this.set("ZENTIDADE_NORMA_01", _this.get("V_ENTIDADE_NORMA_VC_01"));
                if (_this.get("V_MERCADO_VC_01") != null) _this.set("ZMERCADO_01", _this.get("V_MERCADO_VC_01"));
                _this.set("ZNIVEL_REND_DESEMPENHO_01", null);
                _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                if (_this.get("ZNIVEL_REND_DESEMPENHO_01") != null) {
                    if (!market.equals("00006")) {
                        if (norm.equals("00004")) {
                            _this.set("ZNIV_REND_IEC_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_01_DESEMPENHO_" + i));
                            _this.set("ZNIV_REND_NEMA_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_02_DESEMPENHO_" + i));
                            _this.set("ZMERCADO_01", "00006");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_NBR_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZMERCADO_01", _this.get("V_MERCADO_VC_01"));

                        } else if (norm.equals("00006")) {
                            _this.set("ZNIV_REND_IEC_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_02_DESEMPENHO_" + i));
                            _this.set("ZNIV_REND_NEMA_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_01_DESEMPENHO_" + i));
                            _this.set("ZENTIDADE_NORMA_01", "00004");
                            _this.set("ZMERCADO_01", "00006");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_NBR_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZENTIDADE_NORMA_01", _this.get("V_ENTIDADE_NORMA_VC_01"));
                            _this.set("ZMERCADO_01", _this.get("V_MERCADO_VC_01"));
                        }
                    } else {
                        if (norm.equals("00004")) {
                            _this.set("ZNIV_REND_NBR_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_01_DESEMPENHO_" + i));
                            _this.set("ZMERCADO_01", "00010");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_IEC_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZENTIDADE_NORMA_01", "00006");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_NEMA_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZENTIDADE_NORMA_01", _this.get("V_ENTIDADE_NORMA_VC_01"));
                            _this.set("ZMERCADO_01", _this.get("V_MERCADO_VC_01"));
                        } else if (norm.equals("00006")) {
                            _this.set("ZNIV_REND_NBR_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_02_DESEMPENHO_" + i));
                            _this.set("ZMERCADO_01", "00010");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_NEMA_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZENTIDADE_NORMA_01", "00004");
                            _this.util().executeRuleGroup(_this, crit_group, "Calculate");
                            _this.set("ZNIV_REND_IEC_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                            _this.set("ZENTIDADE_NORMA_01", _this.get("V_ENTIDADE_NORMA_VC_01"));
                            _this.set("ZMERCADO_01", _this.get("V_MERCADO_VC_01"));
                        }
                    }
                }

                _this.set("ZNIVEL_REND_DESEMPENHO_01", null);
                if (!market.equals("00006") && _this.number("V_FREQ_DESEMPENHO_AGRUPADA_01") == 50 && (_this.get("ZNIVEL_REND_01_DESEMPENHO_" + i) == null || _this.get("ZNIVEL_REND_02_DESEMPENHO_" + i) == null) && _this.number("V_POT_KW_DESEMPENHO_AGRUP_01") <= 200 && _this.number("V_POT_KW_DESEMPENHO_AGRUP_01") >= 0.12 && (_this.string("ZPOLARIDADE_COMPLETA_01").equals("00001") || _this.string("ZPOLARIDADE_COMPLETA_01").equals("00003") || _this.string("ZPOLARIDADE_COMPLETA_01").equals("00009") || _this.string("ZPOLARIDADE_COMPLETA_01").equals("00016"))) {

                    _this.util().executeRuleGroup(_this, 2038, "Calculate");
                    table_A.addAll(_this.getList("V_COEF_INTERPOLACAO_A_01"));
                    table_B.addAll(_this.getList("V_COEF_INTERPOLACAO_B_01"));
                    table_C.addAll(_this.getList("V_COEF_INTERPOLACAO_C_01"));
                    table_D.addAll(_this.getList("V_COEF_INTERPOLACAO_D_01"));
                    effLevel.clear();
                    if (table_A != null && table_B != null && table_C != null && table_D != null) {
                        for (int k = 1; k <= 4; k++) {
                            Double interpolation = table_A.get(k - 1) * Math.pow(Math.log10(powerkw), 3) + table_B.get(k - 1) * Math.pow(Math.log10(powerkw), 2) + table_C.get(k - 1) * Math.log10(powerkw) + table_D.get(k - 1);
                            Double round = Double.valueOf(Math.round(interpolation * 10));
                            interpolation = round/10;
                            effLevel.add(interpolation);
                        }
                        _this.set("V_NIVEL_REND_INTERPOLACAO_01", effLevel);

                        if (efficiency >= effLevel.get(3)) _this.set("ZNIVEL_REND_DESEMPENHO_01", "00004");
                        else if (efficiency >= effLevel.get(2)) _this.set("ZNIVEL_REND_DESEMPENHO_01", "00003");
                        else if (efficiency >= effLevel.get(1)) _this.set("ZNIVEL_REND_DESEMPENHO_01", "00002");
                        else if (efficiency >= effLevel.get(0)) _this.set("ZNIVEL_REND_DESEMPENHO_01", "00001");
                        if (_this.string("V_ENTIDADE_NORMA_VC_01").equals("00004") && _this.get("ZNIVEL_REND_01_DESEMPENHO_" + i) == null) _this.set("ZNIVEL_REND_01_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                        else if (_this.string("V_ENTIDADE_NORMA_VC_01").equals("00006") && _this.get("ZNIVEL_REND_02_DESEMPENHO_" + i) == null) _this.set("ZNIVEL_REND_02_DESEMPENHO_" + i, _this.get("ZNIVEL_REND_DESEMPENHO_01"));
                        _this.set("ZNIVEL_REND_DESEMPENHO_01", null);
                    }
                }
            }
        }
    }
}