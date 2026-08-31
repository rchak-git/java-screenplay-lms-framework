# Gherkin-First Serenity BDD Task Generator

**Role**: Senior Serenity BDD Test Automation Architect
**Purpose**: Generate a Screenplay Task that accepts Gherkin DataTable Maps (`Map<String, String>`).

## Contract Rules
- MUST implement `net.serenitybdd.screenplay.Task`.
- MUST declare a SINGLE PUBLIC constructor accepting `Map<String, String>`.
- MUST use static factory method `withData(Map<String, String> data)` returning `Tasks.instrumented(...)`.
- DO NOT generate file-reading / YAML constructors or private constructors.
- Use imperative verb class naming without "Task" suffixes or prefixes (e.g., `UpdateProfile`).
- Use Serenity's `@Step` annotation for living documentation.

## Input Parameters
- **Class Name**: UpdateProfile
- **Package Name**: com.learningmate.screenplay.apps.moodle.task
- **UI Locator Class**: MoodleEditProfileUi
- **UI_CLASS_FULL_IMPORT**: com.learningmate.screenplay.apps.moodle.ui.MoodleEditProfileUi
- **MAP_FIELD_RULES**:
  - "City/town" -> Enter.theValue(value).into(MoodleEditProfileUi.CITY_TOWN_INPUT)
  - "Country"   -> SelectFromOptions.byVisibleText(value).from(MoodleEditProfileUi.COUNTRY_SELECT)
  - Submit      -> Click.on(MoodleEditProfileUi.UPDATE_PROFILE_BUTTON)

## Canonical Code Blueprint
package ${PACKAGE_NAME};

import ${UI_CLASS_FULL_IMPORT};
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.Map;

public class ${CLASS_NAME} implements Task {

    private final Map<String, String> data;

    public ${CLASS_NAME}(Map<String, String> data) {
        this.data = data;
    }

    public static ${CLASS_NAME} withData(Map<String, String> data) {
        return instrumented(${CLASS_NAME}.class, data);
    }

    @Override
    @Step("{0} updates profile fields")
    public <T extends Actor> void performAs(T actor) {
        if (data != null) {
            data.forEach((field, value) -> {
                if ("City/town".equalsIgnoreCase(field)) {
                    actor.attemptsTo(Enter.theValue(value).into(${UI_CLASS}.CITY_TOWN_INPUT));
                } else if ("Country".equalsIgnoreCase(field)) {
                    actor.attemptsTo(SelectFromOptions.byVisibleText(value).from(${UI_CLASS}.COUNTRY_SELECT));
                }
            });
        }
        actor.attemptsTo(Click.on(${UI_CLASS}.UPDATE_PROFILE_BUTTON));
    }
}