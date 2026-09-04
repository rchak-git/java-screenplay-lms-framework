package com.learningmate.screenplay.core.ui.component;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class TextBoxField implements UIField {

    private final Target target;

    public TextBoxField(Target target) {
        this.target = target;
    }

    @Override
    public Target getTarget() {
        return this.target;
    }

    @Override
    public void populate(Actor actor, String value) {
        if (value != null && !value.trim().isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(value).into(this.target)
            );
        }
    }

    @Override
    public String value(Actor actor) {
        return this.target.resolveFor(actor).getValue();
    }
}