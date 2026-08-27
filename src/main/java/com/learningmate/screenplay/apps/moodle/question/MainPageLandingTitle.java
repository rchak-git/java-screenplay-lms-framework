package com.learningmate.screenplay.apps.moodle.question;

import com.learningmate.screenplay.apps.moodle.ui.MoodleLoginUi;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.Question;
import com.learningmate.screenplay.core.question.TextOf;

public class MainPageLandingTitle implements Question<String> {

    public static Question<String> text() {
        return new MainPageLandingTitle();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Reads the visible text from the LANDING_PAGE_TITLE target
            return TextOf.field(MoodleLoginUi.LANDING_PAGE_TITLE).answeredBy(actor);
    }
}