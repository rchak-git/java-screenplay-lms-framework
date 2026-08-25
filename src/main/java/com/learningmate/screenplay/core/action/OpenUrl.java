package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;

public class OpenUrl implements Performable {

    private final String url;

    private OpenUrl(String url) {
        this.url = url;
    }

    public static OpenUrl to(String url) {
        return new OpenUrl(url);
    }

    @Override
    public void performAs(Actor actor) {
        BrowseTheWeb.as(actor).getDriver().get(url);
    }
}