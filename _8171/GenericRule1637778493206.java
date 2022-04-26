package _8171;

import net.weg.eng.service.object.ComponentBase;

public class GenericRule1637778493206 {

    private static final GenericRule1637778493206 INSTANCE = new GenericRule1637778493206();

    public static GenericRule1637778493206 getInstance() {
        return GenericRule1637778493206.INSTANCE;
    }
    public void execute(ComponentBase _this) {
        String ruleId = _this.getCurrentRule().getRuleVersionId().toString();
        _this.util().add("LOG_CERTIFICACAO_02","INMETRO" + "#" + ruleId + "#ZREFRIGERACAO_01" +"#" + _this.getTranslate("compliance_VerificaRefrigeracao_8171_LOG_01"));
    }
}