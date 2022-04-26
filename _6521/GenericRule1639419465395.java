package _6521;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1639419465395 {

    private static final GenericRule1639419465395 INSTANCE = new GenericRule1639419465395();

    public static GenericRule1639419465395 getInstance() {
        return GenericRule1639419465395.INSTANCE;
    }

    public void execute(final ComponentBase _this) {
        _this.set("CENTRO_PRODUCAO", _this.getRoot().get("CENTRO_PRODUCAO"));
        _this.set("ZCENTRO_VC_01", _this.getRoot().get("ZCENTRO_VC_01"));
        _this.set("ZCONTROLE_USUARIO_W22_01", _this.getRoot().get("ZCONTROLE_USUARIO_W22_01"));
        _this.set("ZCERTIFICACAO_01", _this.getRoot().get("ZCERTIFICACAO_01"));
        _this.set("ZCERTIFICACAO_MOTOR_01", _this.getRoot().get("ZCERTIFICACAO_MOTOR_01"));
        _this.set("ZMATERIAL_INVOLUCRO_01", _this.getRoot().get("ZMATERIAL_INVOLUCRO_01"));
        _this.set("ZGRUPO_CARCACA_01", _this.getRoot().get("ZGRUPO_CARCACA_01"));
        _this.set("ZCLASSE_ISOLAMENTO_01", _this.getRoot().get("ZCLASSE_ISOLAMENTO_01"));
        _this.set("ZFASE_01", _this.getRoot().get("ZFASE_01"));
        _this.set("ZTEMPERATURA_AMBIENTE_MAX_01", _this.getRoot().get("ZTEMPERATURA_AMBIENTE_MAX_01"));
        _this.set("ZINVOLUCRO_01", _this.getRoot().get("ZINVOLUCRO_01"));
        _this.set("V_MAIOR_POTENCIA_KW_01", _this.getRoot().get("V_MAIOR_POTENCIA_KW_01"));
        _this.set("ZFREQUENCIA_COMERCIAL_01", _this.getRoot().get("ZFREQUENCIA_COMERCIAL_01"));
        _this.set("V_MAIOR_TENSAO_01", _this.getRoot().get("V_MAIOR_TENSAO_01"));
        _this.set("ZREFRIGERACAO_01", _this.getRoot().get("ZREFRIGERACAO_01"));
        _this.set("ZFATOR_SERVICO_01", _this.getRoot().get("ZFATOR_SERVICO_01"));
        _this.set("ZDETALHE1_01", _this.getRoot().get("ZDETALHE1_01"));
        _this.set("ZTIPO_POLARIDADE_01", _this.getRoot().get("ZTIPO_POLARIDADE_01"));
        _this.set("V_MENOR_POTENCIA_KW_01", _this.getRoot().get("V_MENOR_POTENCIA_KW_01"));
        _this.set("ZPOLARIDADE_COMPLETA_01", _this.getRoot().get("ZPOLARIDADE_COMPLETA_01"));
        _this.set("ZAPLICACAO_MOTOR_01", _this.getRoot().get("ZAPLICACAO_MOTOR_01"));
        _this.getParent().getChildByAlias("projeto_eletrico").set("V_MAIOR_TENSAO_01", _this.getRoot().get("V_MAIOR_TENSAO_01"));
        _this.getParent().getChildByAlias("projeto_eletrico").set("V_MAIOR_POTENCIA_KW_01", _this.getRoot().get("V_MAIOR_POTENCIA_KW_01"));
        _this.getParent().getChildByAlias("projeto_eletrico").set("V_MENOR_POTENCIA_KW_01", _this.getRoot().get("V_MENOR_POTENCIA_KW_01"));

    }
}