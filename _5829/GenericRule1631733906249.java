package _5829;

import net.weg.eng.service.object.ComponentBase;

/**
 * Java source code rule.
 */
public class GenericRule1631733906249 {

    /**
     * Singleton reference holder.
     */
    private static final GenericRule1631733906249 INSTANCE = new GenericRule1631733906249();

    /**
     * Singleton method accessor.
     *
     * @return instance
     */
    public static GenericRule1631733906249 getInstance() {
        return GenericRule1631733906249.INSTANCE;
    }

    /**
     * Method invoked by Maestro rule engine.
     *
     * @param _this current context execution.
     */
    public void execute(final ComponentBase _this) {

        //tensoes
        _this.set("V_MENOR_TENSAO_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_TENSAO_60HZ_01"));
        _this.set("V_MENOR_TENSAO_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_TENSAO_50HZ_01"));
        _this.set("V_MAIOR_TENSAO_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_TENSAO_60HZ_01"));
        _this.set("V_MAIOR_TENSAO_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_TENSAO_50HZ_01"));
        _this.set("V_MAIOR_TENSAO_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_TENSAO_01"));
        _this.set("V_MENOR_TENSAO_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_TENSAO_01"));

        //potencias kw
        _this.set("V_MENOR_POTENCIA_KW_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_KW_01"));
        _this.set("V_MAIOR_POTENCIA_KW_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_KW_01"));
        _this.set("V_MENOR_POTENCIA_KW_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_KW_60HZ_01"));
        _this.set("V_MENOR_POTENCIA_KW_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_KW_50HZ_01"));
        _this.set("V_MAIOR_POTENCIA_KW_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_KW_60HZ_01"));
        _this.set("V_MAIOR_POTENCIA_KW_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_KW_50HZ_01"));

        //potencias cv
        _this.set("V_MENOR_POTENCIA_CV_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_CV_01"));
        _this.set("V_MAIOR_POTENCIA_CV_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_CV_01"));
        _this.set("V_MENOR_POTENCIA_CV_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_CV_60HZ_01"));
        _this.set("V_MENOR_POTENCIA_CV_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_POTENCIA_CV_50HZ_01"));
        _this.set("V_MAIOR_POTENCIA_CV_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_CV_60HZ_01"));
        _this.set("V_MAIOR_POTENCIA_CV_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_POTENCIA_CV_50HZ_01"));

        //rendimento
        _this.set("V_MENOR_RENDIMENTO_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_RENDIMENTO_60HZ_01"));
        _this.set("V_MENOR_RENDIMENTO_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_RENDIMENTO_50HZ_01"));
        _this.set("V_MAIOR_RENDIMENTO_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_RENDIMENTO_60HZ_01"));
        _this.set("V_MAIOR_RENDIMENTO_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_RENDIMENTO_50HZ_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_IEC_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_IEC_60HZ_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_IEC_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_IEC_50HZ_01"));
        _this.set("V_MAIOR_NIVEL_RENDIMENTO_IEC_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_NIVEL_RENDIMENTO_IEC_60HZ_01"));
        _this.set("V_MAIOR_NIVEL_RENDIMENTO_IEC_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_NIVEL_RENDIMENTO_IEC_50HZ_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_NEMA_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_NEMA_60HZ_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_NEMA_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_NEMA_50HZ_01"));
        _this.set("V_MAIOR_NIVEL_RENDIMENTO_NEMA_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_NIVEL_RENDIMENTO_NEMA_60HZ_01"));
        _this.set("V_MAIOR_NIVEL_RENDIMENTO_NEMA_50HZ_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_NIVEL_RENDIMENTO_NEMA_50HZ_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_IEC_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_IEC_01"));
        _this.set("V_MENOR_NIVEL_RENDIMENTO_NEMA_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_NIVEL_RENDIMENTO_NEMA_01"));
        _this.set("V_RENDIMENTO_NEMA_PADRONIZADO_60HZ_01", _this.getCmp("../projeto_eletrico").get("V_RENDIMENTO_NEMA_PADRONIZADO_60HZ_01"));
        _this.set("V_ATENDE_NIVEL_RENDIMENTO_NORMA_01", _this.getCmp("../projeto_eletrico").get("V_ATENDE_NIVEL_RENDIMENTO_NORMA_01"));

        //Frequencia
        _this.set("V_MAIOR_FREQUENCIA_01", _this.getCmp("../projeto_eletrico").get("V_MAIOR_FREQUENCIA_01"));
        _this.set("V_MENOR_FREQUENCIA_01", _this.getCmp("../projeto_eletrico").get("V_MENOR_FREQUENCIA_01"));



    }

}