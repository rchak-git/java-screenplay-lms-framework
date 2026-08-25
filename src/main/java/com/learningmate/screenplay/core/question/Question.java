package com.learningmate.screenplay.core.question;

import com.learningmate.screenplay.core.actor.Actor;

@FunctionalInterface
public interface Question<T> {
    T answeredBy(Actor actor);
}