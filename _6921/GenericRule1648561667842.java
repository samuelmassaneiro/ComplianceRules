package _6921;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1648561667842 {

    private static final GenericRule1648561667842 INSTANCE = new GenericRule1648561667842();

    public static GenericRule1648561667842 getInstance() {
        return GenericRule1648561667842.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);
        double maiorTensao = transformed.number("V_MAIOR_TENSAO_01");
        boolean temEnsaioImpulso = transformed.string("ZENSAIO_ESPECIFICO_01").contains("00006");
        return maiorTensao > 1000.0 && !transformed.string("ZENSAIO_ESPECIFICO_01").contains("00006");

    }

}