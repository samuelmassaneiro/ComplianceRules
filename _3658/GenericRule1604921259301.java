package _3658;

import net.weg.eng.service.object.ComponentBase;

import static net.weg.engineering.math.util.CustomUnitMeasurement.CV;

import static javax.measure.unit.SI.KILO;
import static javax.measure.unit.SI.WATT;

import java.text.DecimalFormat;
import java.math.RoundingMode;

public class GenericRule1604921259301 {

    private static final GenericRule1604921259301 INSTANCE = new GenericRule1604921259301();

    public static GenericRule1604921259301 getInstance() {
        return GenericRule1604921259301.INSTANCE;
    }

    public boolean execute(ComponentBase _this) {
        ComponentBase vc = _this.getCmp("../");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        Double powerKw = Double.valueOf(decimalFormat.format(CV.getConverterTo(KILO((WATT))).convert(vc.number("ZPOTENCIA_ESTATISTICA_CV_01"))).replaceAll(",","."));
        String polarity = vc.string("ZPOLARIDADE_COMPLETA_01");

        return polarity.equals("00001") && powerKw <= 2.20 ||
                polarity.equals("00003") && powerKw <= 1.10 ||
                polarity.equals("00009") && powerKw <= 0.75 ||
                polarity.equals("00016") && powerKw <= 0.55;
    }

}