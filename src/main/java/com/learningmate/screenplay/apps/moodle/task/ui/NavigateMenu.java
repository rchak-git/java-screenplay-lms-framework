package com.learningmate.screenplay.apps.moodle.task.ui;


import com.learningmate.screenplay.apps.moodle.ui.MoodleNavigationUi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavigateMenu implements Task {

    private final String menuItem;

    public NavigateMenu(String menuItem) {
        this.menuItem = menuItem;
    }

    /**
     * Creates an instrumented NavigateMenu task instance for Serenity BDD reporting.
     *
     * @param menuItem the name of the menu option (e.g., "Profile", "Grades")
     * @return instrumented NavigateMenu task
     */
    public static NavigateMenu to(String menuItem) {
        return instrumented(NavigateMenu.class, menuItem);
    }

    @Override
    @Step("{0} navigates to menu item '#menuItem'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(MoodleNavigationUi.USER_MENU_TOGGLE),
                Click.on(MoodleNavigationUi.MENU_OPTION_BY_NAME.of(menuItem))
        );
    }
}