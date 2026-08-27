package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.question.MainPageLandingTitle;
import com.learningmate.screenplay.apps.moodle.task.LoginAs;
import com.learningmate.screenplay.core.ability.BrowseTheWeb;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.actor.Actor;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefinitions {

    private Actor actor;
    private WebDriver driver;

    @Before
    public void setUpDriver() {
        // Fetch Serenity's centrally managed WebDriver instance
        this.driver = ThucydidesWebDriverSupport.getDriver();
        this.driver.manage().window().maximize();
    }

    @Given("{string} is on the Moodle login page")
    public void actorIsOnLoginPage(String actorName) {
        this.actor = Actor.named(actorName);

        // Pass Serenity's driver into your custom BrowseTheWeb ability
        this.actor.can(BrowseTheWeb.with(this.driver));

        this.actor.attemptsTo(
                OpenUrl.to("https://sandbox.moodledemo.net/login/index.php")
        );
    }
    @When("he logs in with username {string} and password {string}")
    public void actorLogsInWithCredentials(String username, String password) {
        // Reuses class-level actor
        this.actor.attemptsTo(
                LoginAs.withCredentials(username, password)
        );
    }

    @Then("he should see the Title of Page {string}")
    public void actorShouldSeeTitleOfPage(String expectedTitle) {
        // Reuses class-level actor
        String actualTitle = MainPageLandingTitle.text().answeredBy(this.actor);
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }
}