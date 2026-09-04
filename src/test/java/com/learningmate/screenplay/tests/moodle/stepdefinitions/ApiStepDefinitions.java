package com.learningmate.screenplay.tests.moodle.stepdefinitions;


import com.learningmate.screenplay.apps.moodle.task.api.SeedStudentUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class ApiStepDefinitions {

    @Given("{string} has seeded a new student account via API:")
    public void hasSeededANewStudentAccountViaApi(String actorName, DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        String uniqueSuffix = String.valueOf(System.currentTimeMillis());
        String username = data.get("username") + "_" + uniqueSuffix;
        String email = "user_" + uniqueSuffix + "@example.com";
        String password = data.get("password");

        // Save in memory for UI step
        theActorCalled(actorName).remember("SEEDED_USERNAME", username);
        theActorCalled(actorName).remember("SEEDED_PASSWORD", password);

        // Build dynamic map
        Map<String, String> userPayload = new HashMap<>(data);
        userPayload.put("username", username);
        userPayload.put("email", email);

        theActorCalled(actorName).attemptsTo(
                SeedStudentUser.withDetails(userPayload)
        );
    }
}