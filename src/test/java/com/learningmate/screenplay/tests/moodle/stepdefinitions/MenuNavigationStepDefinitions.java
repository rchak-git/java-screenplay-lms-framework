package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.task.ui.NavigateMenu;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class MenuNavigationStepDefinitions {

    @When("he navigates to his user profile page")
    public void heNavigatesToHisUserProfilePage() {
        // Reuses the actor currently active on stage from previous steps
        theActorInTheSpotlight().attemptsTo(
                NavigateMenu.to("Profile")
        );
    }
}