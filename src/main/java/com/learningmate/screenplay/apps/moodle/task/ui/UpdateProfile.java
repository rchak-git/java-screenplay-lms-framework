package com.learningmate.screenplay.apps.moodle.task.ui;

import com.learningmate.screenplay.apps.moodle.ui.MoodleEditProfileUi;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.Map;

public class UpdateProfile implements Task {

    private final Map<String, String> profileData;

    // MUST be public so Serenity's instrumented() reflection can instantiate it
    public UpdateProfile(Map<String, String> profileData) {
        this.profileData = profileData;
    }

    public static UpdateProfile withData(Map<String, String> profileData) {
        return instrumented(UpdateProfile.class, profileData);
    }

    @Override
    @Step("{0} updates profile details")
    public <T extends Actor> void performAs(T actor) {
        if (profileData != null) {
            profileData.forEach((field, value) -> {
                if ("City/town".equalsIgnoreCase(field)) {
                    actor.attemptsTo(Enter.theValue(value).into(MoodleEditProfileUi.CITY_TOWN_INPUT));
                } else if ("Country".equalsIgnoreCase(field)) {
                    actor.attemptsTo(SelectFromOptions.byVisibleText(value).from(MoodleEditProfileUi.COUNTRY_SELECT));
                }
            });
        }

        actor.attemptsTo(Click.on(MoodleEditProfileUi.UPDATE_PROFILE_BUTTON));
    }
}