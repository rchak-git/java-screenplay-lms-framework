package com.learningmate.screenplay.core.action.dropdown;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;
import com.learningmate.screenplay.core.util.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectFromOptions implements Performable {

    private final Target dropdownTarget;

    private final String visibleText;


     private  SelectFromOptions(Target target,String inputText)
     {

         this.dropdownTarget = target;
         this.visibleText = inputText;

     }

    public static SelectFromOptionsBuilder byVisibleText(String visibleText) {
        return new SelectFromOptionsBuilder(visibleText);
    }

    public static class SelectFromOptionsBuilder {
        private final String visibleText;

        public SelectFromOptionsBuilder(String visibleText) {
            this.visibleText = visibleText; // Remembers "SUCCESS"
        }

        // STEP 2: Finishes the sentence and returns the final Action!
        public SelectFromOptions from(Target dropdownTarget) {
            return new SelectFromOptions(dropdownTarget, this.visibleText);
        }
    }
    @Override
    public void performAs(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement element = WaitUtils.waitUntilClickable(driver, dropdownTarget);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);

    }
}
