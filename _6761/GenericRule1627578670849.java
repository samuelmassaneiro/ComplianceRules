package _6761;

import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContextQuery;

import java.util.Map;
import java.util.HashMap;

public class GenericRule1627578670849 {

    private static final GenericRule1627578670849 INSTANCE = new GenericRule1627578670849();

    private static final String FDC_CONNECTOR_NAME = "fdcLegacyQueryConnector";

    private static final String POWER_QUERY = "SELECT D.CD_POTENCIA_PEDIDO " +
            "FROM POTENCIA_CV_COMPLEMENT_FDC A, " +
            "POTENCIA_CV_FDC B, " +
            "POTENCIA_PEDIDO_FDC D " +
            "WHERE B.CD_POTENCIA_CV = A.CD_POTENCIA_CV " +
            "AND D.CD_POTENCIA_CV = A.CD_POTENCIA_CV " +
            "AND :NR_POTENCIAS = " +
            "   (SELECT COUNT(CD_POTENCIA_CV) " +
            "   FROM POTENCIA_CV_COMPLEMENT_FDC C " +
            "   WHERE CD_POTENCIA_CV IN ( " +
            "       SELECT CD_POTENCIA_CV " +
            "       FROM POTENCIA_CV_COMPLEMENT_FDC " +
            "       WHERE VL_POTENCIA_CV = :VL_POTENCIA_CV " +
            "       AND CD_SEQUENCIA = 1) ";

    private static final String POWER_2_QUERY = "AND CD_POTENCIA_CV IN ( " +
            "   SELECT CD_POTENCIA_CV " +
            "   FROM POTENCIA_CV_COMPLEMENT_FDC " +
            "   WHERE VL_POTENCIA_CV = :VL_POTENCIA_CV " +
            "   AND CD_SEQUENCIA = 2) ";

    private static final String POWER_3_QUERY = "AND CD_POTENCIA_CV IN ( " +
            "   SELECT CD_POTENCIA_CV " +
            "   FROM POTENCIA_CV_COMPLEMENT_FDC " +
            "   WHERE VL_POTENCIA_CV = :VL_POTENCIA_CV " +
            "   AND CD_SEQUENCIA = 3) ";

    private static final String END_POWER_QUERY = "AND D.ID_UNIDADE_POTENCIA = ':ID_UNIDADE_POTENCIA' " +
            "AND C.CD_POTENCIA_CV = A.CD_POTENCIA_CV GROUP BY CD_POTENCIA_CV)";

    public static GenericRule1627578670849 getInstance() {
        return GenericRule1627578670849.INSTANCE;
    }

    private String getUnitMeasure(String market) {
        return market.equals("00009") || market.equals("00003") ||
                market.equals("00030") || market.equals("00004") ? "HP" :
                market.equals("00006") ? "CV" : "KW";
    }

    private String getFdcPowerCodeQuery(Map<String, Double> powers, String powerUnit) {
        String query = POWER_QUERY.replace(":NR_POTENCIAS", "" + powers.size()).replace(":VL_POTENCIA_CV", powers.get("POWER_1").toString());

        if (powers.size() > 1) {
            query += POWER_2_QUERY.replace(":VL_POTENCIA_CV", powers.get("POWER_2").toString());
        }
        if (powers.size() > 2) {
            query += POWER_3_QUERY.replace(":VL_POTENCIA_CV", powers.get("POWER_3").toString());
        }
        query += END_POWER_QUERY.replace(":ID_UNIDADE_POTENCIA", powerUnit);
        return query;
    }

    public void execute(final ComponentBase _this) {
        final ObjectContextQuery queryCtx = _this.getDecorated(ObjectContextQuery.class);
        final ComponentBase variantConfiguration = _this.getCmp("../");
        final String powerUnit = getUnitMeasure(variantConfiguration.string("ZMERCADO_01"));
        final String[] powerFdc = _this.string("POTENCIA_CV_DESEMPENHOS").split(";");
        final Map<String, Double> powers = new HashMap<String, Double>();

        for (int i = 0; i < powerFdc.length; ++i) {
            if (powers.get("POWER_1") == null) {
                powers.put("POWER_1", Double.parseDouble(powerFdc[i].replaceAll(",",".")));
            } else if (powers.get("POWER_2") == null && powers.get("POWER_1") != Double.parseDouble(powerFdc[i].replaceAll(",","."))) {
                powers.put("POWER_2", Double.parseDouble(powerFdc[i].replaceAll(",",".")));
            } else if (powers.get("POWER_3") == null && powers.get("POWER_1") != Double.parseDouble(powerFdc[i].replaceAll(",","."))) {
                powers.put("POWER_3", Double.parseDouble(powerFdc[i].replaceAll(",",".")));
            }

            if (_this.string("CD_TENSAO_DETALHE").equals("M")) break;
        }
        _this.util().debug(getFdcPowerCodeQuery(powers, powerUnit));
        String queryResult = queryCtx.getStringResult(FDC_CONNECTOR_NAME, getFdcPowerCodeQuery(powers, powerUnit), new HashMap<String,Object>());
        if (queryResult != null) {
            _this.set("ZPOTENCIA_MULTI_UNIDADE_01", queryResult);
        }
    }
}