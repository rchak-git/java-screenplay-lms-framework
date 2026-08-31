package com.learningmate.screenplay.apps.moodle.question;

import com.learningmate.screenplay.apps.moodle.ui.MoodleLoginUi;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class MainPageLandingTitle implements Question<String> {

    public static Question<String> text() {
        return new MainPageLandingTitle();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Asks Serenity's built-in Text question using your MoodleLoginUi target
        actor.attemptsTo(
                WaitUntil.the(MoodleLoginUi.LANDING_PAGE_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
        return Text.of(MoodleLoginUi.LANDING_PAGE_TITLE).answeredBy(actor);
    }
}