package com.learningmate.screenplay.tests.saucedemo;

import com.learningmate.screenplay.apps.saucedemo.task.Login;
import com.learningmate.screenplay.apps.saucedemo.ui.LoginPageUi;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.TextOf;
import com.learningmate.screenplay.core.util.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPageTest {

    private Actor actor;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actor = Actor.named("Rajib").can(BrowseTheWeb.with(driver));
    }

    @Test
    public void testValidUserLogin() {
        actor.attemptsTo(
                Login.withDataId("VALID_USER")
        );

        Map<String, String> expectedData = DataReader.getRecord("saucedemo/login_saucedemo_data.yaml", "VALID_USER");
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedData.get("expectedUrl"));
    }

    @Test
    public void testLockedOutUserLogin() {
        actor.attemptsTo(
                Login.withDataId("LOCKED_USER")
        );

        Map<String, String> expectedData = DataReader.getRecord("saucedemo/login_saucedemo_data.yaml", "LOCKED_USER");
        String actualError = TextOf.field(LoginPageUi.ERROR_MESSAGE).answeredBy(actor);

        Assert.assertEquals(actualError, expectedData.get("errorMessage"));
    }

    @Test
    public void testInvalidCredentialsLogin() {
        actor.attemptsTo(
                Login.withDataId("INVALID_USER")
        );

        Map<String, String> expectedData = DataReader.getRecord("saucedemo/login_saucedemo_data.yaml", "INVALID_USER");
        String actualError = TextOf.field(LoginPageUi.ERROR_MESSAGE).answeredBy(actor);

        Assert.assertEquals(actualError, expectedData.get("errorMessage"));
    }

    @Test
    public void testMissingPasswordLogin() {
        actor.attemptsTo(
                Login.withDataId("MISSING_PASSWORD")
        );

        Map<String, String> expectedData = DataReader.getRecord("saucedemo/login_saucedemo_data.yaml", "MISSING_PASSWORD");
        String actualError = TextOf.field(LoginPageUi.ERROR_MESSAGE).answeredBy(actor);

        Assert.assertEquals(actualError, expectedData.get("errorMessage"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
