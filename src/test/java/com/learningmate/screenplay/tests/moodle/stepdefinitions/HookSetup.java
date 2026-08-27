package com.learningmate.screenplay.tests.moodle.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class HookSetup {

    @Before
    public void setTheStage() {
        // Sets up the global actor cast equipped with web browsing abilities
        OnStage.setTheStage(new OnlineCast());
    }
}