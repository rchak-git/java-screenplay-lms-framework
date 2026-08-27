package com.learningmate.screenplay.core.question.table;

import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.Question;
import com.learningmate.screenplay.core.question.TextOf;
import com.learningmate.screenplay.core.ui.Target;

public class ReadTableCell implements Question<String> {

    private final Target cellTarget;

    public ReadTableCell(Target cellTarget) {
        this.cellTarget = cellTarget;
    }

    public static ReadTableCell valueOf(Target cellTarget) {
        return new ReadTableCell(cellTarget);
    }

    @Override
    public String answeredBy(Actor actor) {
        return TextOf.field(cellTarget).answeredBy(actor);
    }
}