package com.learningmate.screenplay.core.question;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;

public class TextOf implements Question<String> {

    private final Target target;

    private TextOf(Target target) {
        this.target = target;
    }

    public static TextOf field(Target target) {
        return new TextOf(target);
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor)
                .getDriver()
                .findElement(target.getLocator())
                .getText();
    }
}