package _3157;

import net.weg.eng.bean.object.ObjectHeader;
import net.weg.eng.bean.rule.OperatorType;
import net.weg.eng.filter.ObjectFilterDynamic;
import net.weg.eng.service.object.ComponentBase;
import net.weg.eng.service.object.ObjectContext;

import java.util.List;

/**
 * Java source code rule.
 */
public class GenericRule1637846724381 {

    /**
     * Singleton reference holder.
     */
    private static final GenericRule1637846724381 INSTANCE = new GenericRule1637846724381();

    /**
     * Singleton method accessor.
     *
     * @return instance
     */
    public static GenericRule1637846724381 getInstance() {
        return GenericRule1637846724381.INSTANCE;
    }


    /**
     * Method invoked by Maestro rule engine.
     *
     * @param _this current context execution.
     */
    public void execute(final ComponentBase _this) {
        ComponentBase transformed = _this.getCmp("../../sec_dados_container->sec_transformed").getDecorated(ComponentBase.class);
        ComponentBase vc = _this.getCmp("../../sec_dados_container->variant_configuration").getDecorated(ComponentBase.class);


        String ZAPLICACAO_MOTOR_01 = "00082";
        String[] duplaPolaridade = {"",""};
        String ZCARCACA_COM_COMPLEMENTO_01 = vc.string("ZCARCACA_COM_COMPLEMENTO_01");
        String ZPOLARIDADE_COMPLETA_01 = transformed.string("ZPOLARIDADE_COMPLETA_01");
        String ZORGANISMO_CERTIFICA_NAVAL_01 = transformed.string("ZORGANISMO_CERTIFICA_NAVAL_01");
        String ZFREQUENCIA_01 = transformed.string("ZFREQUENCIA_01");
        String POTENCIA_MAXIMA = _this.string("POTENCIA_MAXIMA");

        //debug
        _this.util().debug("1439485");
        _this.util().debug("ZAPLICACAO_MOTOR_01: "+ZAPLICACAO_MOTOR_01);
        _this.util().debug("ZCARCACA_COM_COMPLEMENTO_01: "+ZCARCACA_COM_COMPLEMENTO_01);
        _this.util().debug("ZPOLARIDADE_COMPLETA_01: "+ZPOLARIDADE_COMPLETA_01);
        _this.util().debug("ZORGANISMO_CERTIFICA_NAVAL_01: "+ZORGANISMO_CERTIFICA_NAVAL_01);
        _this.util().debug("ZFREQUENCIA_01: "+ZFREQUENCIA_01);
        _this.util().debug("1439485");


        final String OBJECT_TYPE_NAME = "LINK_POTENCIA_MAXIMA_NAVAL";
        ObjectFilterDynamic filter = new ObjectFilterDynamic(OBJECT_TYPE_NAME);
        filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZAPLICACAO_MOTOR_01", OperatorType.Equality, ZAPLICACAO_MOTOR_01);
        filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZCARCACA_COM_COMPLEMENTO_01", OperatorType.Equality, ZCARCACA_COM_COMPLEMENTO_01);
        filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZORGANISMO_CERTIFICA_NAVAL_01", OperatorType.Equality, ZORGANISMO_CERTIFICA_NAVAL_01);
        filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZFREQUENCIA_01", OperatorType.Equality, ZFREQUENCIA_01);

        if(transformed.string("ZTIPO_POLARIDADE_01").equals("00001")){
            if(ZPOLARIDADE_COMPLETA_01.equals("00004")) duplaPolaridade = new String[]{"00003", "00001"};
            //whitelist polaridades;
            filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZPOLARIDADE_COMPLETA_01", OperatorType.Equality, duplaPolaridade[0]);
            filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZPOLARIDADE_COMPLETA_01", OperatorType.Equality, duplaPolaridade[1]);
        }else filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "ZPOLARIDADE_COMPLETA_01", OperatorType.Equality, ZPOLARIDADE_COMPLETA_01);

        filter.add(OBJECT_TYPE_NAME, OBJECT_TYPE_NAME, "POTENCIA_MAXIMA", OperatorType.Equality);


        List < ObjectHeader > objectHeaders = filter.filter().getResults();
        if (objectHeaders != null && !objectHeaders.isEmpty()) {
            _this.set("POTENCIA_MAXIMA", objectHeaders.get(0).getObjectContext().get("POTENCIA_MAXIMA"));
        } else {
            _this.util().debug("Rule 1439485 - Potencia maxima nao encontrada");
        }
    }

}