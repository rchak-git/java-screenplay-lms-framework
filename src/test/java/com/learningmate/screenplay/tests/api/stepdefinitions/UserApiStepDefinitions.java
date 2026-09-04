package com.learningmate.screenplay.tests.api.stepdefinitions;



import com.learningmate.screenplay.tests.api.tasks.FetchUserData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserApiStepDefinitions {

    private Actor actor;

    @Given("{string} is an API client targeting {string}")
    public void configureBaseUrl(String actor, String baseUrl) {
        this.actor = Actor.named(actor);
        this.actor.can(CallAnApi.at(baseUrl));
    }

    @When("he sends a GET request to {string}")
    public void sendGetRequest(String resource) {
        this.actor.attemptsTo(
                FetchUserData.fromEndpoint(resource)
        );
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        SerenityRest.restAssuredThat(response ->
                response.statusCode(expectedStatusCode)
        );
    }

    @Then("the user's first name should be {string}")
    public void verifyFirstName(String expectedFirstName) {
        String actualFirstName = SerenityRest.lastResponse()
                .jsonPath()
                .getString("data.first_name");

        assertThat(actualFirstName, equalTo(expectedFirstName));
    }
}