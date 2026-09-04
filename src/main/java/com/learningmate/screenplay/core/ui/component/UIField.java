package com.learningmate.screenplay.core.ui.component;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Polymorphic interface representing a reusable UI field component.
 * Implementations handle specific field types (text boxes, dropdowns, etc.)
 * with consistent populate and value retrieval semantics.
 */
public interface UIField {

    /**
     * Returns the Serenity Target for this UI field.
     *
     * @return the Target representing this field's locator
     */
    Target getTarget();

    /**
     * Populates this field with the given value.
     * Behavior is implementation-specific:
     * - TextBoxField: clears existing text and types the new value
     * - DropdownField: selects the option by value or text
     *
     * @param actor the Screenplay actor performing the action
     * @param value the value to populate
     */
    void populate(Actor actor, String value);

    /**
     * Retrieves the current value of this field.
     * Behavior is implementation-specific:
     * - TextBoxField: reads the input value
     * - DropdownField: reads the selected option text or value
     *
     * @param actor the Screenplay actor performing the action
     * @return the current field value
     */
    String value(Actor actor);
}
