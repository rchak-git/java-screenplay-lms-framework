package com.learningmate.screenplay.apps.moodle.task;

import com.learningmate.screenplay.apps.moodle.ui.MoodleLoginUi;
import com.learningmate.screenplay.core.action.TypeInto;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.Map;

public class LoginAs implements Task {

    private final String username;
    private final String password;

    public LoginAs(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Task withCredentials(String username, String password) {
        return instrumented(LoginAs.class, username, password);
    }

    public static Task withCredentials(Map<String, String> credentials) {
        return instrumented(LoginAs.class, credentials.get("username"), credentials.get("password"));
    }

    @Override
    @Step("{0} logs into Moodle with credentials")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TypeInto.text(username,MoodleLoginUi.USERNAME ),
                TypeInto.text(password,MoodleLoginUi.PASSWORD),
                Click.on(MoodleLoginUi.LOG_IN)
        );
    }
}