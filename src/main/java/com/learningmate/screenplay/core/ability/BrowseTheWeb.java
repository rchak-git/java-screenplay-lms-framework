package com.learningmate.screenplay.core.ability;

import com.learningmate.screenplay.core.actor.Actor;
import org.openqa.selenium.WebDriver;

public class BrowseTheWeb implements Ability {

    private final WebDriver driver;

    // Private constructor enforces static factory method usage
    private BrowseTheWeb(WebDriver driver) {
        this.driver = driver;
    }

    // Static Factory Method
    public static BrowseTheWeb with(WebDriver driver) {
        return new BrowseTheWeb(driver);
    }

    // Helper method to extract the ability directly from an Actor
    public static BrowseTheWeb as(Actor actor) {
        return actor.usingAbilityTo(BrowseTheWeb.class);
    }

    // Accessor for the underlying Selenium driver
    public WebDriver getDriver() {
        return driver;
    }
}