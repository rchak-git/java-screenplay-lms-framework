package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import com.learningmate.screenplay.apps.moodle.task.ResetProfileToDefault;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class HookSetup {

    @Before(order = 1)
    public void setTheStage() {
        // Automatically manages thread-safe actors and WebDrivers for parallel execution
        OnStage.setTheStage(new OnlineCast());
    }
/*
    @Before(value = "@EnsureBaselineProfile", order = 2)
    public void ensureBaselineProfileState() {
        // If an actor is logged in and active, ensure baseline state before step execution
        if (OnStage.theActorInTheSpotlight() != null) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    ResetProfileToDefault.to("Brussels", "Belgium")
            );
        }
    }

 */

    @After
    public void drawTheCurtain() {
        OnStage.drawTheCurtain(); // Cleans up actors and releases browser state
    }
}