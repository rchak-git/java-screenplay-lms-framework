package com.learningmate.screenplay.apps.saucedemo.ui;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public final class LoginPageUi {

    public static final Target USERNAME_INPUT = Target.the("Username Input Field")
            .located(By.id("user-name"));

    public static final Target PASSWORD_INPUT = Target.the("Password Input Field")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON = Target.the("Login Submit Button")
            .located(By.id("login-button"));

    public static final Target ERROR_MESSAGE = Target.the("Error Message Banner")
            .located(By.cssSelector(".error-message-container"));

    private LoginPageUi() {
    }
}
