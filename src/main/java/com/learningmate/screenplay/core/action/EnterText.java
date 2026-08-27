package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;
import com.learningmate.screenplay.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnterText implements Performable {

    private final String text;
    private final Target target;

    private EnterText(String text, Target target) {
        this.text = text;
        this.target = target;
    }

    public static EnterTextBuilder into(Target target) {

        return new EnterTextBuilder(target);
    }


    @Override
    public void performAs(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement element = WaitUtils.waitUntilVisible(driver, target);
        element.clear();
        element.sendKeys(text);
    }

    public static class EnterTextBuilder {
        private final Target target;

        public EnterTextBuilder(Target target) {
            this.target = target;
        }

        public EnterText with(String text) {
            return new EnterText(text, target);
        }

        public EnterText of(String text) {
            return new EnterText(text, target);
        }
    }
}