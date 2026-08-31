package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.ui.MoodleProfileUi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import java.util.Map;

public class ProfileStepDefinitions {

    @Then("he should see his profile name as {string}")
    public void heShouldSeeHisProfileNameAs(String expectedName) {
        // Asks native Serenity Text question using active actor on stage
        theActorInTheSpotlight().should(
                seeThat(Text.of(MoodleProfileUi.PROFILE_NAME_HEADING), equalTo(expectedName))
        );
    }

    @Then("his profile details should match:")
    public void hisProfileDetailsShouldMatch(Map<String, String> expectedProfileData) {
        expectedProfileData.forEach((fieldName, expectedValue) ->
                theActorInTheSpotlight().should(
                        seeThat(Text.of(MoodleProfileUi.PROFILE_FIELD.of(fieldName)), equalTo(expectedValue))
                )
        );
    }

    @And("he clicks on the edit profile link")
    public void heClicksOnTheEditProfileLink() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(MoodleProfileUi.EDIT_PROFILE_LINK)
        );
    }
}