package _6767;

import net.weg.eng.service.object.ComponentBase;
import java.util.List;
import java.util.ArrayList;

public class GenericRule1643655044960 {

    private static final GenericRule1643655044960 INSTANCE = new GenericRule1643655044960();

    public static GenericRule1643655044960 getInstance() {
        return GenericRule1643655044960.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {

        ComponentBase transformed = _this.getCmp("../../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);

        String market = "";
        String plate = "";
        String application = "";
        List tpEspecialidade = new ArrayList();

        market = transformed.string("ZMERCADO_01");
        plate = transformed.string("ZPLACA_PRINCIPAL_CLIENTE_01");
        application = transformed.string("ZAPLICACAO_MOTOR_01");
        try {tpEspecialidade = (List) transformed.get("ZTIPO_ESPECIALIDADE_VC_01");} catch (Exception e) {}

        boolean isMarket = market.equals("00007");
        boolean isPlate = plate.equals("00117") || plate.equals("00177") || plate.equals("00207");
        boolean isTpEspecialidade = tpEspecialidade.contains("00020");
        boolean isW21 = application.equals("00147") && isMarket;
        boolean isW22 = application.equals("00119") && (isPlate || isTpEspecialidade);
        boolean isW50 = application.equals("00191") && (isPlate || isTpEspecialidade);
        boolean isWquattro = application.equals("00101") && (isPlate || isTpEspecialidade);

        return isW21 || isW22 || isW50 || isWquattro;

    }
}