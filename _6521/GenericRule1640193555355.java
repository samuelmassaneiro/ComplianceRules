package _6521;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1640193555355 {

    private static final GenericRule1640193555355 INSTANCE = new GenericRule1640193555355();

    public static GenericRule1640193555355 getInstance() {
        return GenericRule1640193555355.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        _this.set("ZCENTRO_VC_01", _this.getRoot().get("ZCONTROLE_USUARIO_W22_01"));
        _this.set("CENTRO_PRODUCAO", _this.getRoot().get("CENTRO_PRODUCAO"));
        _this.set("ZFASE_01", _this.getRoot().get("ZFASE_01"));

    }

}