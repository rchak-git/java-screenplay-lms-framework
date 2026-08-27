package com.learningmate.screenplay.tests.moodle;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/moodle",
        glue = "com.learningmate.screenplay.tests.moodle.stepdefinitions"
)
public class MoodleTestSuite {
}