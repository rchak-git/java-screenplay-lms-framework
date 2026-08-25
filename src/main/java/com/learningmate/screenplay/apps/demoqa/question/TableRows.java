package com.learningmate.screenplay.apps.demoqa.question;

import com.learningmate.screenplay.apps.demoqa.ui.WebTableUi;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TableRows implements Question<Boolean> {

    private final String expectedText;

    private TableRows(String expectedText) {
        this.expectedText = expectedText;
    }

    public static TableRows containsText(String expectedText) {
        return new TableRows(expectedText);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // 1. Filter the web table using the search box
        actor.attemptsTo(
                Click.on(WebTableUi.SEARCH_BOX),
                EnterText.into(WebTableUi.SEARCH_BOX).of(expectedText)
        );

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 2. Query table rows using the Target locator from WebTableUi
        try {
            return wait.until(d -> d.findElements(WebTableUi.TABLE_ROWS.getLocator())
                    .stream()
                    .anyMatch(row -> row.getText().contains(expectedText)));
        } catch (Exception e) {
            return false;
        }
    }
}
