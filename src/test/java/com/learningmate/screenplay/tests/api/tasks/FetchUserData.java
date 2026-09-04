package com.learningmate.screenplay.tests.api.tasks;


import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class FetchUserData implements Task {

    private final String resource;

    public FetchUserData(String resource) {
        this.resource = resource;
    }

    public static FetchUserData fromEndpoint(String resource) {
        return Tasks.instrumented(FetchUserData.class, resource);
    }

    @Override
    @Step("{0} sends a GET request to '#resource'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }
}