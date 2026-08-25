package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;
import com.learningmate.screenplay.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;

public class Click implements Performable {

    private final Target target;

    private Click(Target target) {
        this.target = target;
    }

    public static Click on(Target target) {
        return new Click(target);
    }


    @Override
    public void performAs(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WaitUtils.waitUntilClickable(driver, target).click();
    }
}