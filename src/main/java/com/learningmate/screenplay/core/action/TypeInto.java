package com.learningmate.screenplay.core.action;


import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class TypeInto implements Interaction {

    private final String text;
    private final Target target;

    public TypeInto(String text, Target target) {
        this.text = text;
        this.target = target;
    }

    public static TypeInto text(String text, Target target) {
        return instrumented(TypeInto.class, text, target);
    }

    @Override
    @Step("{0} types '#text' into #target")
    public <T extends Actor> void performAs(T actor) {
        // 1. Ensure element is clickable
        actor.attemptsTo(
                WaitUntil.the(target, isClickable()).forNoMoreThan(10).seconds()
        );

        // 2. Clear element
        WebElement element = target.resolveFor(actor);
        element.click();
        element.clear();

        // 3. Re-resolve target immediately before typing
        WebElement freshElement = target.resolveFor(actor);
        freshElement.sendKeys(text);

        // 4. Brief micro-pause to let DOM value settle BEFORE focus shifts
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {}
    }
}
