package com.learningmate.screenplay.tests;

import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import org.testng.annotations.Test;

public class JavaMechanicsTest {

    @Test
    public void testLambdaAndVarargs() {
        Actor student = Actor.named("Student_01");

        // Inline Performable using Lambda syntax
        Performable inlineTask = actor ->
                System.out.println(actor.getName() + " is viewing the Canvas LMS Dashboard.");

        // Passing multiple tasks via Varargs
        student.attemptsTo(
                inlineTask,
                actor -> System.out.println(actor.getName() + " clicked on 'Enrolled Courses'."),
                actor -> System.out.println(actor.getName() + " opened Module 1.")
        );
    }
}