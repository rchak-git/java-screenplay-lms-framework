package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.task.ui.UpdateProfile;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class EditProfileStepDefinitions {

    @And("he updates his profile details:")
    public void heUpdatesHisProfileDetails(DataTable dataTable) {
        theActorInTheSpotlight().attemptsTo(
                UpdateProfile.withData(dataTable.asMap(String.class, String.class))
        );
    }
}