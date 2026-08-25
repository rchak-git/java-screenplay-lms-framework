package com.learningmate.screenplay.apps.saucedemo.task;

import com.learningmate.screenplay.apps.saucedemo.ui.LoginPageUi;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.util.DataReader;

import java.util.Map;

public class Login implements Performable {

    private final String filePath;
    private final String dataId;

    private static final String DEFAULT_FILE_PATH = "saucedemo/login_saucedemo_data.yaml";

    private Login(String filePath, String dataId) {
        this.filePath = filePath;
        this.dataId = dataId;
    }

    public static Login usingFile(String filePath, String dataId) {
        return new Login(filePath, dataId);
    }

    public static Login withDataId(String dataId) {
        return new Login(DEFAULT_FILE_PATH, dataId);
    }

    @Override
    public void performAs(Actor actor) {
        Map<String, String> data = DataReader.getRecord(filePath, dataId);

        actor.attemptsTo(
                OpenUrl.to("https://www.saucedemo.com/"),
                EnterText.into(LoginPageUi.USERNAME_INPUT).of(data.get("username")),
                EnterText.into(LoginPageUi.PASSWORD_INPUT).of(data.get("password")),
                Click.on(LoginPageUi.LOGIN_BUTTON)
        );
    }
}
