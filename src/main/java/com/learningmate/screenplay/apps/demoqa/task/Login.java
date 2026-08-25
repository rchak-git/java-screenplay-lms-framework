package com.learningmate.screenplay.apps.demoqa.task;

import com.learningmate.screenplay.apps.demoqa.ui.CanvasLoginPage;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;

public class Login implements Performable {

    private final String username;
    private final String password;

    private Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static LoginBuilder as(String username) {
        return new LoginBuilder(username);
    }

    @Override
    public void performAs(Actor actor) {
        actor.attemptsTo(
                OpenUrl.to("https://canvas.instructure.com/login/canvas"),
                EnterText.into(CanvasLoginPage.USERNAME_FIELD).of(username),
                EnterText.into(CanvasLoginPage.PASSWORD_FIELD).of(password),
                Click.on(CanvasLoginPage.LOGIN_BUTTON)
        );
    }

    public static class LoginBuilder {
        private final String username;

        public LoginBuilder(String username) {
            this.username = username;
        }

        public Login withPassword(String password) {
            return new Login(username, password);
        }
    }
}
