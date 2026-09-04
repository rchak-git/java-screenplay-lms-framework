package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.question.MainPageLandingTitle;
import com.learningmate.screenplay.apps.moodle.task.ui.LoginAs;
import com.learningmate.screenplay.core.config.EnvironmentConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;


import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {

    @Given("{string} is on the Moodle login page")
    public void actorIsOnLoginPage(String actorName) {
        String loginUrl = EnvironmentConfig.getMoodleBaseUrl() + "/login/index.php";
        theActorCalled(actorName).attemptsTo(
                Open.url(loginUrl)
        );
    }

    @When("he logs in with username {string} and password {string}")
    public void actorLogsInWithCredentials(String username, String password) {
        // Retrieves active actor on thread
        theActorInTheSpotlight().attemptsTo(
                LoginAs.withCredentials(username, password)
        );
    }

    @Then("he should see the Title of Page {string}")
    public void actorShouldSeeTitleOfPage(String expectedTitle) {
        // Native Screenplay assertion pattern
       theActorInTheSpotlight().should(
                seeThat(MainPageLandingTitle.text(), equalTo(expectedTitle))
        );




    }

    @Given("{string} is logged into Moodle as a student with credentials:")
    public void isLoggedIntoMoodleWithDataTable(String actorName, io.cucumber.datatable.DataTable dataTable) {
        // 1. Check if dynamic credentials exist in actor memory from API seeding
        String username = theActorCalled(actorName).recall("SEEDED_USERNAME");
        String password = theActorCalled(actorName).recall("SEEDED_PASSWORD");

        // 2. Fall back to Gherkin DataTable if no dynamic credentials were saved
        if (username == null || password == null) {
            Map<String, String> credentials = dataTable.asMap(String.class, String.class);
            username = credentials.get("username");
            password = credentials.get("password");
        }

        actorIsOnLoginPage(actorName);
        actorLogsInWithCredentials(username, password);
        // actorShouldSeeTitleOfPage("Moodle 5.2 sandbox demo");
    }
}