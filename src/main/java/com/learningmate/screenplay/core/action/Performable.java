package com.learningmate.screenplay.core.action;

import com.learningmate.screenplay.core.actor.Actor;

@FunctionalInterface
public interface Performable {
    void performAs(Actor actor);
}