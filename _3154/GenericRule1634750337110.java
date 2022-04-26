package _3154;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1634750337110 {

    private static final GenericRule1634750337110 INSTANCE = new GenericRule1634750337110();

    public static GenericRule1634750337110 getInstance() {
        return GenericRule1634750337110.INSTANCE;
    }

    public void execute(ComponentBase _this) {

        String[][] caracts = {
                {
                        "ZTIPO_PRODUTO_01"
                },
                {
                        "ZFREQUENCIA_01"
                },
                {
                        "ZGRUPO_CARCACA_01"
                },
                {
                        "ZPOLARIDADE_COMPLETA_01"
                },
                {
                        "ZPOTENCIA_ESTATISTICA_CV_01"
                },
                {
                        "ZTEMPERATURA_AMBIENTE_MAX_01"
                },
                {
                        "ZREFRIGERACAO_01"
                },
                {
                        "ZMETODO_PARTIDA_01"
                },
                {
                        "ZDETALHE2_01"
                },
                {
                        "ZPOTENCIA_MULTI_UNIDADE_01"
                },
                {
                        "ZTIPO_POLARIDADE_01"
                },
                {
                        "ZMERCADO_01"
                },
                {
                        "ZFASE_01"
                },
                {
                        "ZINVOLUCRO_01"
                },
                {
                        "ZAPLICACAO_MOTOR_01"
                },
                {
                        "ZMATERIAL_INVOLUCRO_01"
                },
                {
                        "ZENTIDADE_NORMA_01"
                },
                {
                        "ZREGIME_SERVICO_01"
                },
                {
                        "ZTEMPERATURA_AMBIENTE_MIN_01"
                },
                {
                        "ZKIT_PORTA_ESCOVA_01"
                },
                {
                        "ZKIT_PORTA_ESCOVA_03"
                },
                {
                        "ZTENSAO_RESIST_AQUEC_01"
                },
                {
                        "ZTENSAO_SEM_CABO_01",
                        "V_MAIOR_TENSAO_01"
                },
                {
                        "ZFATOR_SERVICO_01",
                        "YFATOR_SERVICO"
                },
                {
                        "ZDETALHE1_01"
                },
                {
                        "ZCARCACA_COMERCIAL_01"
                },
                {
                        "ZCARCACA_COM_COMPLEMENTO_01"
                },
                {
                        "ZGRAU_PROTECAO_01"
                },
                {
                        "ZPROTETOR_TERMICO_FENOLICO_01"
                },
                {
                        "ZCATEGORIA_01"
                }
        };


        String msg1 = "867099 - Favor informar valor para a característica ";
        String msg2 = ". Valor cadastrado incorreto ou faltante.";

        for (int i = 0; i < caracts.length; i++) {
            String caract1 = "";
            String caract2 = "";
            String valor1 = "";
            String valor2 = "";

            try {
                caract1 = caracts[i][0];
                try {
                    valor1 = _this.get(caract1).toString();
                    _this.util().debug("caract1 " + caract1 + " - valor1 " + valor1);
                } catch (Exception e) {
                    //caract1 = "-";
                    _this.util().debug("caract1 erro val " + caract1);
                }
            } catch (Exception e) {
                //valos1 = "-";
                _this.util().debug("caract1 erro caract " + i);
            }

            try {
                caract2 = caracts[i][1];
                try {
                    valor2 = _this.get(caract2).toString();
                    _this.util().debug("caract2 " + caract2 + " - valor2 " + valor2);
                } catch (Exception e) {
                    //caract1 = "-";
                    _this.util().debug("caract2 erro val " + caract2);
                }
            } catch (Exception e) {
                //valos2 = "-";
                _this.util().debug("caract2 erro caract " + i);
            }

            if (valor1.equals("") && valor2.equals("")) {
                _this.util().debug("valor1.equals(" + ") && valor2.equals(" + ")");
                if (!caract1.equals("") && !caract2.equals("")) {
                    _this.util().debug("!caract1.equals(" + ") && !caract2.equals(" + ")");
                    _this.getMessages().error("Erro", msg1 + caract1 + " ou " + caract2 + msg2);
                } else if (!caract1.equals("")) {
                    _this.util().debug("!caract1.equals(" + ")");
                    if (caract1.equals("ZCATEGORIA_01")) {
                        _this.util().debug("caract1.equals(ZCATEGORIA_01)");
                        if (_this.get("ZFASE_01").toString().equals("00012")) {
                            _this.util().debug("_this.get(ZFASE_01).toString().equals(00012)");
                            _this.getMessages().error("Erro", msg1 + caract1 + msg2);
                        } else {
                            _this.util().debug("ELSE");
                            _this.getMessages().error("Erro", msg1 + caract1 + msg2);
                        }
                    } else if (!caract2.equals("")) {
                        _this.util().debug("!caract2.equals(" + ")");
                        _this.getMessages().error("Erro", msg1 + caract1 + msg2);
                    }
                }
            }
        }
    }
}