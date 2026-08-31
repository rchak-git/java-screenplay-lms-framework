package com.learningmate.screenplay.apps.moodle.task;



import com.learningmate.screenplay.apps.moodle.ui.MoodleEditProfileUi;
import com.learningmate.screenplay.apps.moodle.ui.MoodleProfileUi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ResetProfileToDefault implements Task {

    private final String defaultCity;
    private final String defaultCountry;

    public ResetProfileToDefault(String defaultCity, String defaultCountry) {
        this.defaultCity = defaultCity;
        this.defaultCountry = defaultCountry;
    }

    public static ResetProfileToDefault to(String defaultCity, String defaultCountry) {
        return instrumented(ResetProfileToDefault.class, defaultCity, defaultCountry);
    }

    @Override
    @Step("{0} resets profile details back to default")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                LoginAs.withCredentials("student","sandbox24"),
                Click.on(MoodleProfileUi.EDIT_PROFILE_LINK),
                Enter.theValue(defaultCity).into(MoodleEditProfileUi.CITY_TOWN_INPUT),
                SelectFromOptions.byVisibleText(defaultCountry).from(MoodleEditProfileUi.COUNTRY_SELECT),
                Click.on(MoodleEditProfileUi.UPDATE_PROFILE_BUTTON)
        );
    }
}