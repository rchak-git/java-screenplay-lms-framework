package com.learningmate.screenplay.core.ui.component;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.questions.SelectedValue;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * UIField implementation for dropdown/select fields.
 * Handles both HTML <select> and React-based dropdowns.
 * Supports selection by value and verification of selected option.
 */
public class DropdownField implements UIField {

    private final Target target;
    private final boolean isReactDropdown;

    public DropdownField(Target target) {
        this(target, false);
    }

    public DropdownField(Target target, boolean isReactDropdown) {
        this.target = target;
        this.isReactDropdown = isReactDropdown;
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Override
    public void populate(Actor actor, String value) {
        if (isReactDropdown) {
            selectReactDropdown(actor, value);
        } else {
            actor.attemptsTo(
                    SelectFromOptions.byValue(value).from(target)
            );
        }
    }

    @Override
    public String value(Actor actor) {
        return actor.asksFor(SelectedValue.of(target));
    }

    /**
     * Handles selection in React-based dropdowns by clicking
     * and selecting from dynamic options.
     * Assumes React dropdown renders options as clickable elements.
     *
     * @param actor the Screenplay actor
     * @param value the option value to select
     */
    private void selectReactDropdown(Actor actor, String value) {
        actor.attemptsTo(
                Click.on(target),
                Click.on(By.xpath(String.format("//div[@data-value='%s'] | //*[text()='%s']", value, value)))
        );
    }
}
