package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.ui.MoodleProfileUi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;



import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

public class ProfileStepDefinitions {

    @Then("he should see his profile name as {string}")
    public void heShouldSeeHisProfileNameAs(String expectedName) {
        // Asks native Serenity Text question using active actor on stage
        theActorInTheSpotlight().should(
                seeThat(Text.of(MoodleProfileUi.PROFILE_NAME_HEADING), equalTo(expectedName))
        );
    }

    @Then("{string} profile details should match:")
    public void profileDetailsShouldMatch(String actorName, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> expectedData = dataTable.asMap(String.class, String.class);

        String expectedFirstName = expectedData.get("Firstname");
        String expectedLastName = expectedData.get("Lastname");
        String expectedFullName = expectedFirstName + " " + expectedLastName; // "Rajib Automation"

        // Retrieve the dynamic email saved in memory during API seeding
        String expectedEmail = theActorCalled(actorName).recall("SEEDED_EMAIL");
        if (expectedEmail == null) {
            expectedEmail = expectedData.get("Email address");
        }

        // Perform Screenplay assertions against MoodleProfileUi targets
        theActorCalled(actorName).should(
                seeThat(Text.of(MoodleProfileUi.PROFILE_NAME_HEADING), containsString(expectedFullName)),
                seeThat(Text.of(MoodleProfileUi.EMAIL_ADDRESS), containsString(expectedEmail))
        );
    }

    @And("he clicks on the edit profile link")
    public void heClicksOnTheEditProfileLink() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(MoodleProfileUi.EDIT_PROFILE_LINK)
        );
    }
}