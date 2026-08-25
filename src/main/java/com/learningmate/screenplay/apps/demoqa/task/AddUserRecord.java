package com.learningmate.screenplay.apps.demoqa.task;

import com.learningmate.screenplay.apps.demoqa.ui.WebTableUi;
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.util.DataReader;

import java.util.Map;

public class AddUserRecord implements Performable {

    private final String filePath;
    private final String dataId;

    private AddUserRecord(String filePath, String dataId) {
        this.filePath = filePath;
        this.dataId = dataId;
    }

    public static AddUserRecord usingFile(String filePath, String dataId) {
        return new AddUserRecord(filePath, dataId);
    }

    public static AddUserRecord withDataId(String dataId) {
        return new AddUserRecord("demoqa/webtables_data.yaml", dataId);
    }

    @Override
    public void performAs(Actor actor) {
        // Read test data into a Map using the file path passed from the caller
        Map<String, String> data = DataReader.getRecord(filePath, "webtable_data." +dataId);

        actor.attemptsTo(
                Click.on(WebTableUi.ADD_BUTTON),
                EnterText.into(WebTableUi.FIRST_NAME_FIELD).of(data.get("firstName")),
                EnterText.into(WebTableUi.LAST_NAME_FIELD).of(data.get("lastName")),
                EnterText.into(WebTableUi.EMAIL_FIELD).of(data.get("email")),
                EnterText.into(WebTableUi.AGE_FIELD).of(String.valueOf(data.get("age"))),
                EnterText.into(WebTableUi.SALARY_FIELD).of(String.valueOf(data.get("salary"))),
                EnterText.into(WebTableUi.DEPARTMENT_FIELD).of(data.get("department")),
                Click.on(WebTableUi.SUBMIT_BUTTON)
        );
    }
}