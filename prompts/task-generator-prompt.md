# Screenplay Task Generator Contract

**Role**: Lead Test Automation Architect
**Purpose**: Generate a Screenplay Task that strictly adheres to the team's canonical framework contract.

## Contract Rules
- MUST implement `com.learningmate.screenplay.core.action.Performable`.
- MUST use static factory methods `withDataId(dataId)` and `usingFile(filePath, dataId)` to read data via `DataReader`.
- DO NOT append "Task" to the class name (e.g., use `Login`, NOT `LoginTask`).
- DO NOT hardcode credentials or field values inside `performAs()`.
- MUST use exact imports specified in the canonical structure.

## Input Parameters
- **Class Name**: Login
- **Package Name**: com.learningmate.screenplay.apps.saucedemo.task
- **UI Locator Class**: LoginPageUi
- **YAML Default File Path**: saucedemo/login_saucedemo_data.yaml
- **UI_CLASS_FULL_IMPORT**: com.learningmate.screenplay.apps.saucedemo.ui.LoginPageUi

## Canonical Code Blueprint
```java
package ${PACKAGE_NAME};

import ${UI_CLASS_FULL_IMPORT};
import com.learningmate.screenplay.core.action.Click;
import com.learningmate.screenplay.core.action.EnterText;
import com.learningmate.screenplay.core.action.OpenUrl;
import com.learningmate.screenplay.core.action.Performable;
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.util.DataReader;

import java.util.Map;

public class ${CLASS_NAME} implements Performable {

    private final String filePath;
    private final String dataId;

    private static final String DEFAULT_FILE_PATH = "${YAML_PATH}";

    private ${CLASS_NAME}(String filePath, String dataId) {
        this.filePath = filePath;
        this.dataId = dataId;
    }

    public static ${CLASS_NAME} usingFile(String filePath, String dataId) {
        return new ${CLASS_NAME}(filePath, dataId);
    }

    public static ${CLASS_NAME} withDataId(String dataId) {
        return new ${CLASS_NAME}(DEFAULT_FILE_PATH, dataId);
    }

    @Override
    public void performAs(Actor actor) {
        Map<String, String> data = DataReader.getRecord(filePath, dataId);

        actor.attemptsTo(
                OpenUrl.to("[https://www.saucedemo.com/](https://www.saucedemo.com/)"),
                EnterText.into(${UI_CLASS}.USERNAME_INPUT).of(data.get("username")),
                EnterText.into(${UI_CLASS}.PASSWORD_INPUT).of(data.get("password")),
                Click.on(${UI_CLASS}.LOGIN_BUTTON)
        );
    }
}