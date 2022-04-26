package _6773;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1625846079281 {

    private static final GenericRule1625846079281 INSTANCE = new GenericRule1625846079281();

    public static GenericRule1625846079281 getInstance() {
        return GenericRule1625846079281.INSTANCE;
    }

    public boolean execute(final ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);
        String organismoNaval = transformed.string("ZORGANISMO_CERTIFICA_NAVAL_01");
        return !"00010".equals(organismoNaval) && !"".equals(organismoNaval);
    }

}