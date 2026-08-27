package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutoCompleteSelect implements Performable {

    private final Target inputTarget;
    private final Target suggestionTemplateTarget;
    private final String textToType;
    private final String optionToSelect;
    private final int timeoutInSeconds;

    private AutoCompleteSelect(Target inputTarget, Target suggestionTemplateTarget, String textToType, String optionToSelect, int timeoutInSeconds) {
        this.inputTarget = inputTarget;
        this.suggestionTemplateTarget = suggestionTemplateTarget;
        this.textToType = textToType;
        this.optionToSelect = optionToSelect;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    /**
     * Fluent Builder Entry Point
     */
    public static AutoCompleteSelect into(Target inputTarget, Target suggestionTemplateTarget, String textToType, String optionToSelect) {
        return new AutoCompleteSelect(inputTarget, suggestionTemplateTarget, textToType, optionToSelect, 10);
    }

    public static AutoCompleteSelect into(Target inputTarget, Target suggestionTemplateTarget, String textToType, String optionToSelect, int timeoutInSeconds) {
        return new AutoCompleteSelect(inputTarget, suggestionTemplateTarget, textToType, optionToSelect, timeoutInSeconds);
    }

    @Override
    public void performAs(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        // 1. Locate and focus input element
        WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(inputTarget.getLocator()));
        inputElement.clear();
        inputElement.sendKeys(textToType);

        // 2. Resolve dynamic suggestion target with the expected text option
        Target resolvedSuggestionTarget = suggestionTemplateTarget.of(optionToSelect);

        // 3. Wait for suggestion to appear in DOM and click
        WebElement suggestionElement = wait.until(ExpectedConditions.elementToBeClickable(resolvedSuggestionTarget.getLocator()));
        suggestionElement.click();
    }
}