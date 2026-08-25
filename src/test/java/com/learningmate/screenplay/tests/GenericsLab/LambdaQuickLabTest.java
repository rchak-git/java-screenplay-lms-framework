package com.learningmate.screenplay.tests.GenericsLab;

import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import org.testng.annotations.Test;

public class LambdaQuickLabTest {

    @Test
    public void testScreenplayInlineTasks() {
        Actor instructor = Actor.named("Rajib");

        // Inline task using Lambda
        Performable openCanvas = actor ->
                System.out.println(actor.getName() + " navigated to Canvas LMS.");

        // Inline task logging out
        Performable logout = actor ->
                System.out.println(actor.getName() + " logged out of Canvas.");

        // Executing tasks sequentially via Varargs
        instructor.attemptsTo(
                openCanvas,
                logout
        );
    }
}