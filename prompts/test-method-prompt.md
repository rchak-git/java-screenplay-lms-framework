# Screenplay Test Class Generator Contract

**Role**: Lead Test Automation Architect

**Purpose**: Generate a TestNG unit test class that executes Screenplay Tasks and Questions using dynamic YAML data.

## Rules
- MUST instantiate `Actor actor = Actor.named("Tester")` in `@BeforeMethod`.
- Tasks MUST be invoked via `actor.attemptsTo(...)`.
- Assertions MUST extract actual values using `actor.asks(...)` and compare against `DataReader.getRecord(...)`.
- DO NOT hardcode test data or assertions inside the `@Test` methods; pull all expectations from the YAML data file using the corresponding `dataId`.

## Input Parameters
- **Target Directory Path**: `${TARGET_DIR}` (e.g., `src/test/java/com/learningmate/screenplay/tests/saucedemo/`)
- **File Name**: `${FILE_NAME}` (e.g., `LoginPageTest.java`)
- **Class Name**: `${TEST_CLASS_NAME}` (e.g., `LoginPageTest`)
- **Package Name**: `${PACKAGE_NAME}` (e.g., `com.learningmate.screenplay.tests.saucedemo`)
- **Task Executed**: `${TASK_CLASS}` (e.g., `Login`)
- **UI Locator Class**: `${UI_CLASS}` (e.g., `LoginPageUi`)
- **YAML Data File Path**: `${YAML_PATH}` (e.g., `saucedemo/login_saucedemo_data.yaml`)

## Output Instruction
Create the file `${FILE_NAME}` inside directory `${TARGET_DIR}` using the content below:

## Canonical Blueprint
package ${PACKAGE_NAME};

import com.learningmate.screenplay.apps.saucedemo.task.${TASK_CLASS};
import com.learningmate.screenplay.apps.saucedemo.ui.${UI_CLASS};
import com.learningmate.screenplay.core.actor.Actor;
import com.learningmate.screenplay.core.question.TextOf;
import com.learningmate.screenplay.core.util.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

public class ${TEST_CLASS_NAME} {

    private Actor actor;

    @BeforeMethod
    public void setUp() {
        actor = Actor.named("Rajib");
    }

    @Test
    public void testValidUserLogin() {
        actor.attemptsTo(
                ${TASK_CLASS}.withDataId("VALID_USER")
        );
    }

    @Test
    public void testLockedOutUserLogin() {
        actor.attemptsTo(
                ${TASK_CLASS}.withDataId("LOCKED_USER")
        );

        Map<String, String> expectedData = DataReader.getRecord("${YAML_PATH}", "LOCKED_USER");
        String actualError = actor.asks(TextOf.field(${UI_CLASS}.ERROR_MESSAGE));

        Assert.assertEquals(actualError, expectedData.get("errorMessage"));
    }

    @Test
    public void testInvalidCredentialsLogin() {
        actor.attemptsTo(
                ${TASK_CLASS}.withDataId("INVALID_USER")
        );

        Map<String, String> expectedData = DataReader.getRecord("${YAML_PATH}", "INVALID_USER");
        String actualError = actor.asks(TextOf.field(${UI_CLASS}.ERROR_MESSAGE));

        Assert.assertEquals(actualError, expectedData.get("errorMessage"));
    }
}