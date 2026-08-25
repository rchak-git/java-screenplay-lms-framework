package com.learningmate.screenplay.core.ui;

import org.openqa.selenium.By;

public class Target {

    private final String description;
    private final By locator;

    // Private constructor called by TargetBuilder
    Target(String description, By locator) {
        this.description = description;
        this.locator = locator;
    }

    // 1. First step in fluent API: Target.the("Canvas Username Field")
    public static TargetBuilder the(String description) {
        return new TargetBuilder(description);
    }

    public String getDescription() {
        return description;
    }

    public By getLocator() {
        return locator;
    }

    // 2. Inner Builder Class
    public static class TargetBuilder {
        private final String description;

        public TargetBuilder(String description) {
            this.description = description;
        }

        // Second step in fluent API: .located(By.id("username"))
        public Target located(By locator) {
            return new Target(this.description, locator);
        }
    }
}