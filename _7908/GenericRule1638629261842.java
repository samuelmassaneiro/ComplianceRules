package _7908;

import net.weg.eng.service.object.ComponentBase;
import java.util.*;

public class GenericRule1638629261842 {

    private static final GenericRule1638629261842 INSTANCE = new GenericRule1638629261842();

    public static GenericRule1638629261842 getInstance() {
        return GenericRule1638629261842.INSTANCE;
    }
    public void execute(ComponentBase _this) {
        String ruleId = _this.getCurrentRule().getRuleVersionId().toString();
        _this.util().add("LOG_CERTIFICACAO_02","UL SEGURA" + "#" + ruleId + "#ZVELOCIDADE_ROTACAO_01" +"#" + _this.getTranslate("compliance_Verificavelocidadederotacao_7908_LOG_01"));
    }
}