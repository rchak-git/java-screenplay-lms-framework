package com.learningmate.screenplay.apps.saucedemo.question;

import com.learningmate.screenplay.apps.saucedemo.ui.SauceDemoPage;
import com.learningmate.screenplay.core.question.Question;
import com.learningmate.screenplay.core.question.TextOf;

public final class SauceDemoQuestions {

    private SauceDemoQuestions() {
    }

    public static Question<String> appHeaderTitle() {
        return TextOf.field(SauceDemoPage.APP_HEADER);
    }
}
