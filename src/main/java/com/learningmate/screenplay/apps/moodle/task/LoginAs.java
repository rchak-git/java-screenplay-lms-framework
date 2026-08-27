package com.learningmate.screenplay.apps.moodle.task;

import com.learningmate.screenplay.apps.moodle.ui.MoodleLoginUi;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import java.util.Map;

public class LoginAs implements Performable {

    private final String userName;
    private final String password;

    private LoginAs(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static LoginAs withCredentials(String userName, String password) {
        return new LoginAs(userName, password);
    }

    // Optional builder for Map-based inputs
    public static LoginAs withCredentials(Map<String, String> credentials) {
        return new LoginAs(
                credentials.get("username"),
                credentials.get("password")
        );
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                EnterText.into(MoodleLoginUi.USERNAME).with(userName),
                EnterText.into(MoodleLoginUi.PASSWORD).with(password),
                Click.on(MoodleLoginUi.LOG_IN)
        );
    }
}