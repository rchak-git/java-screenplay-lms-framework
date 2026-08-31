package com.learningmate.screenplay.apps.moodle.ui;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class MoodleLoginUi {

    public static final Target USERNAME = Target.the("Username input field")
            .located(By.id("username"));

    public static final Target PASSWORD = Target.the("Password input field")
            .located(By.id("password"));

    public static final Target LOG_IN = Target.the("Log in button")
            .located(By.id("loginbtn"));

    public static final Target WELCOME_BACK = Target.the("Welcome back heading")
            .located(By.xpath("//h1[normalize-space()='Welcome back']"));

    public static final Target LANDING_PAGE_TITLE = Target.the("Moodle 5.2 sandbox demo heading")
            .located(By.xpath("//h1[normalize-space()='Moodle 5.2 sandbox demo']"));

}