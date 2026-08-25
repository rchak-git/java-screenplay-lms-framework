package com.learningmate.screenplay.apps.demoqa.question;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.question.Question;

public final class CanvasQuestions {

    private CanvasQuestions() {
    }

    public static Question<String> currentUrl() {
        return actor -> BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
    }
}
