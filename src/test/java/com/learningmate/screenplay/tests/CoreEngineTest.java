package com.learningmate.screenplay.tests;

import com.learningmate.screenplay.core.ability.Ability;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import org.testng.annotations.Test;

// Mock Ability
class LogConsole implements Ability {
    public void print(String message) {
        System.out.println("[CONSOLE]: " + message);
    }
}

// Mock Interaction/Task
class OutputMessage implements Performable {
    private final String text;

    public OutputMessage(String text) {
        this.text = text;
    }

    public static OutputMessage of(String text) {
        return new OutputMessage(text);
    }

    @Override
    public void performAs(Actor actor) {
        actor.usingAbilityTo(LogConsole.class).print(actor.getName() + " says: " + text);
    }
}

public class CoreEngineTest {

    @Test
    public void testActorExecution() {
        Actor instructor = Actor.named("Rajib")
                .can(new LogConsole());

        instructor.attemptsTo(
                OutputMessage.of("Welcome to Canvas LMS Automation!")
        );
    }
}