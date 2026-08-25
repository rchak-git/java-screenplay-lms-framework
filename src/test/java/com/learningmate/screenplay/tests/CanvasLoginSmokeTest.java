package com.learningmate.screenplay.tests;

import com.learningmate.screenplay.apps.demoqa.question.CanvasQuestions;
import com.learningmate.screenplay.apps.demoqa.task.Login;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.actor.Actor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CanvasLoginSmokeTest {

    private WebDriver driver;
    private Actor teacher;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        teacher = Actor.named("Rajib")
                .can(BrowseTheWeb.with(driver));
    }

    @Test
    public void testCanvasLoginInteractions() {
        teacher.attemptsTo(
                Login.as("test_instructor@learningmate.com").withPassword("DummyPassword123")
        );

        Assert.assertTrue(CanvasQuestions.currentUrl().answeredBy(teacher).contains("canvas"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}