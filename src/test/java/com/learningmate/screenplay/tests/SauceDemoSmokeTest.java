/*package com.learningmate.screenplay.tests;

import com.learningmate.screenplay.apps.saucedemo.question.SauceDemoQuestions;
import com.learningmate.screenplay.apps.saucedemo.task.Login;
import com.learningmate.screenplay.apps.saucedemo.ui.SauceDemoPage;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoSmokeTest {

    private WebDriver driver;
    private Actor standardUser;

    private Actor teacher;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        standardUser = Actor.named("Rajib")
                .can(BrowseTheWeb.with(driver));
        teacher =  Actor.named("Rajib")
                .can(BrowseTheWeb.with(driver));
    }

    @Test
    public void testLoginInteractions() {
        standardUser.attemptsTo(
                OpenUrl.to("https://www.saucedemo.com/"),
                EnterText.into(SauceDemoPage.USERNAME_FIELD).of("standard_user"),
                EnterText.into(SauceDemoPage.PASSWORD_FIELD).of("secret_sauce"),
                Click.on(SauceDemoPage.LOGIN_BUTTON)
        );
    }

    @Test
    public void testHighLevelLoginAndAssertion() {
        // 1. Perform high-level Task
        teacher.attemptsTo(
                LoginManual.as("standard_user").withPassword("secret_sauce")
        );

        // 2. Query application state using Question
        String headerTitle = SauceDemoQuestions.appHeaderTitle().answeredBy(teacher);

        // 3. Assert result
        Assert.assertEquals(headerTitle, "Swag Labs");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

 */