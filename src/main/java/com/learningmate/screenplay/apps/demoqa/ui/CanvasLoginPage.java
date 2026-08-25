package com.learningmate.screenplay.apps.demoqa.ui;

import com.learningmate.screenplay.core.ui.Target;
import org.openqa.selenium.By;

public final class CanvasLoginPage {

    public static final Target USERNAME_FIELD = Target.the("Username Field").located(By.id("pseudonym_session_unique_id"));
    public static final Target PASSWORD_FIELD = Target.the("Password Field").located(By.id("pseudonym_session_password"));
    public static final Target LOGIN_BUTTON = Target.the("Login Button").located(By.cssSelector("button[type='submit']"));

    private CanvasLoginPage() {
    }
}
