package com.learningmate.screenplay.tests.moodle.stepdefinitions;



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

    @After
    public void drawTheCurtain() {
        OnStage.drawTheCurtain(); // Cleans up actors and releases browser state
    }
}