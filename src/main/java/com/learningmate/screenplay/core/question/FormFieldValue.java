package com.learningmate.screenplay.core.question;

import com.learningmate.screenplay.core.ui.component.UIField;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Generic Screenplay Question for retrieving form field values.
 * Works with any UIField implementation to read the current field value.
 *
 * Usage:
 *   String value = actor.asksFor(FormFieldValue.of(uiField));
 */
public class FormFieldValue implements Question<String> {

    private final UIField field;

    public FormFieldValue(UIField field) {
        this.field = field;
    }

    public static FormFieldValue of(UIField field) {
        return new FormFieldValue(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        return field.value(actor);
    }

    @Override
    public String toString() {
        return "the value of form field " + field.getTarget();
    }
}
