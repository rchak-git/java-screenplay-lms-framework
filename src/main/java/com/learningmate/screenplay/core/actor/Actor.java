package com.learningmate.screenplay.core.actor;

import com.learningmate.screenplay.core.ability.Ability;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.question.Question;

import java.util.HashMap;
import java.util.Map;

public class Actor {
    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new HashMap<>();

    public Actor(String name) {
        this.name = name;
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    // Ability Management
    public <T extends Ability> Actor can(T ability) {
        this.abilities.put(ability.getClass(), ability);
        return this; // Fluent API
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T usingAbilityTo(Class<T> abilityClass) {
        T ability = (T) abilities.get(abilityClass);
        if (ability == null) {
            throw new IllegalStateException("Actor " + name + " does not have the ability to " + abilityClass.getSimpleName());
        }
        return ability;
    }

    // Execution Core
    public Actor attemptsTo(Performable... tasks) {
        for (Performable task : tasks) {
            task.performAs(this);
        }
        return this;
    }

    public <T> T asks(Question<T> question) {
        return question.answeredBy(this);
    }

    public String getName() {
        return name;
    }
}