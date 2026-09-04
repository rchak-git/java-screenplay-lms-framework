package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.ui.component.UIField;
import com.learningmate.screenplay.core.util.ContextEvaluator;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.Map;

/**
 * Generic Screenplay Task for populating multiple form fields.
 * Resolves field keys from a strict registry, handles dynamic expressions,
 * and invokes UIField.populate for each field.
 *
 * Usage:
 *   actor.attemptsTo(PopulateFields.with(fieldData).from(fieldRegistry));
 */
public class PopulateFields implements Task {

    private final Map<String, String> fieldData;
    private Map<String, UIField> fieldRegistry;

    public PopulateFields(Map<String, String> fieldData) {
        this.fieldData = fieldData;
    }

    public static PopulateFields with(Map<String, String> fieldData) {
        return Tasks.instrumented(PopulateFields.class, fieldData);
    }

    public PopulateFields from(Map<String, UIField> fieldRegistry) {
        this.fieldRegistry = fieldRegistry;
        return this;
    }

    @Override
    @Step("{0} populates form fields")
    public <T extends Actor> void performAs(T actor) {
        if (fieldRegistry == null || fieldRegistry.isEmpty()) {
            throw new IllegalStateException("Field registry is not configured. Call from(registry) before executing.");
        }

        // Evaluate all field values, resolving dynamic expressions
        Map<String, String> evaluatedData = ContextEvaluator.evaluateMap(actor, fieldData);

        // Populate each field
        for (Map.Entry<String, String> entry : evaluatedData.entrySet()) {
            String fieldKey = entry.getKey();
            String fieldValue = entry.getValue();

            UIField field = fieldRegistry.get(fieldKey);
            if (field == null) {
                throw new IllegalArgumentException(
                        "Field '" + fieldKey + "' not found in registry. Available fields: " + fieldRegistry.keySet()
                );
            }

            field.populate(actor, fieldValue);
        }
    }
}
