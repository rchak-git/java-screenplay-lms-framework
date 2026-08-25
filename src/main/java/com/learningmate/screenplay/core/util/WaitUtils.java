package com.learningmate.screenplay.core.util;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public static WebElement waitUntilClickable(WebDriver driver, Target target) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(target.getLocator()));
    }

    public static WebElement waitUntilVisible(WebDriver driver, Target target) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(target.getLocator()));
    }

    public static boolean waitUntilTextPresent(WebDriver driver, Target target, String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(target.getLocator(), text));
    }
}