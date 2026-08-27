package com.learningmate.screenplay.core.action.table;

import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.ui.Target;

public class ClickRowAction implements Performable {


    private final Target cellTarget;


    private ClickRowAction(Target cellTarget) {

        this.cellTarget = cellTarget;
    }

    public static ClickRowAction on(Target target) {

        return new ClickRowAction(target);
    }
     @Override
     public void performAs(Actor actor) {
        actor.attemptsTo(
                Click.on(cellTarget)
        );
    }


}
