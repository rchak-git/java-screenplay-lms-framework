package com.learningmate.screenplay.apps.saucedemo.ui;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public final class SauceDemoPage {

    public static final Target USERNAME_FIELD = Target.the("Username Field").located(By.id("user-name"));
    public static final Target PASSWORD_FIELD = Target.the("Password Field").located(By.id("password"));
    public static final Target LOGIN_BUTTON = Target.the("Login Button").located(By.id("login-button"));
    public static final Target APP_HEADER = Target.the("App Header Title").located(By.cssSelector(".app_logo"));

    private SauceDemoPage() {
    }
}
