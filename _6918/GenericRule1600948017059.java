package _6918;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1600948017059 {

    private static final GenericRule1600948017059 INSTANCE = new GenericRule1600948017059();

    public static GenericRule1600948017059 getInstance() {
        return GenericRule1600948017059.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {

        double ciclo = _this.number("ZFATOR_DURACAO_CICLO_S3_01");
        String regime = _this.string("ZREGIME_SERVICO_01");
        boolean isS3ciclo80 = "00006".equals(regime) && ciclo >= 80.0;
        boolean isS1 = "00003".equals(regime);
        return isS1 || isS3ciclo80;
    }

}