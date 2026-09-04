package com.learningmate.screenplay.tests.api;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features/api",
        glue = "com.learningmate.screenplay.tests.api.stepdefinitions"
)
public class ApiIT {
}