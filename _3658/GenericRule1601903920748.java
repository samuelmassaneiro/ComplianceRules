package _3658;

import net.weg.eng.service.object.ComponentBase;

import static net.weg.engineering.math.util.CustomUnitMeasurement.CV;

import static javax.measure.unit.SI.KILO;
import static javax.measure.unit.SI.WATT;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class GenericRule1601903920748 {

    private static final GenericRule1601903920748 INSTANCE = new GenericRule1601903920748();

    public static GenericRule1601903920748 getInstance() {
        return GenericRule1601903920748.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        final DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        final ComponentBase variantConfiguration = _this.getObject("RULE_SERVICE_VC_10000008").getDecorated(ComponentBase.class);
        final Double powerKw = Double.valueOf(decimalFormat.format(CV.getConverterTo(KILO((WATT))).convert(variantConfiguration.number("ZPOTENCIA_ESTATISTICA_CV_01"))).replaceAll(",","."));
        final String polarity = variantConfiguration.string("ZPOLARIDADE_COMPLETA_01");

        return polarity.equals("00001") && powerKw > 2.20 ||
                polarity.equals("00003") && powerKw > 1.10 ||
                polarity.equals("00009") && powerKw > 0.75 ||
                polarity.equals("00016") && powerKw > 0.55;
    }

}