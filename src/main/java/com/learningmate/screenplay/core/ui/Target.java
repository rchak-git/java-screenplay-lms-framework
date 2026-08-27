package com.learningmate.screenplay.core.ui;

import org.openqa.selenium.By;

public class Target {

    private final String description;
    private final By locator;

    Target(String description, By locator) {
        this.description = description;
        this.locator = locator;
    }

    public static TargetBuilder the(String description) {
        return new TargetBuilder(description);
    }

    public String getDescription() {
        return description;
    }

    public By getLocator() {
        return locator;
    }

    /**
     * Replaces string placeholders like {0}, {1} in dynamic XPaths
     * and returns a new populated Target instance.
     */
    public Target of(Object... params) {
        String rawLocator = this.locator.toString();

        // Remove Selenium 'By.xpath: ' or 'By.cssSelector: ' prefix
        String cleanPath = rawLocator.substring(rawLocator.indexOf(":") + 2);

        // Format placeholders {0}, {1}, etc.
        for (int i = 0; i < params.length; i++) {
            cleanPath = cleanPath.replace("{" + i + "}", String.valueOf(params[i]));
        }

        String formattedDescription = String.format(this.description, params);
        return new Target(formattedDescription, By.xpath(cleanPath));
    }

    public static class TargetBuilder {
        private final String description;

        public TargetBuilder(String description) {
            this.description = description;
        }

        public Target located(By locator) {
            return new Target(this.description, locator);
        }

        public Target locatedBy(String xpath) {
            return new Target(this.description, By.xpath(xpath));
        }
    }
}