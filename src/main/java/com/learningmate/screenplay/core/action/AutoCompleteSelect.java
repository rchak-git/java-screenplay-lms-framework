package com.learningmate.screenplay.core.action;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AutoCompleteSelect implements Interaction {

    private final Target inputTarget;
    private final Target suggestionTemplateTarget;
    private final String textToType;
    private final String optionToSelect;

    public AutoCompleteSelect(Target inputTarget, Target suggestionTemplateTarget,
                              String textToType, String optionToSelect) {
        this.inputTarget = inputTarget;
        this.suggestionTemplateTarget = suggestionTemplateTarget;
        this.textToType = textToType;
        this.optionToSelect = optionToSelect;
    }

    public static AutoCompleteSelect into(Target inputTarget, Target suggestionTemplateTarget,
                                          String textToType, String optionToSelect) {
        return instrumented(AutoCompleteSelect.class, inputTarget, suggestionTemplateTarget, textToType, optionToSelect);
    }

    @Override
    @Step("{0} enters '#textToType' into autocomplete field and selects '#optionToSelect'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(textToType).into(inputTarget),
                Click.on(suggestionTemplateTarget.of(optionToSelect))
        );
    }
}