# Global Copilot Instructions for LMS Serenity Screenplay Framework

## 1. Architectural Stack
- **Framework:** Serenity BDD 4.1.14 + JUnit 4 + Cucumber BDD.
- **Pattern:** Screenplay Pattern ONLY. Do not suggest Page Object Model (POM).

## 2. Coding Standards
- **Tasks & Interactions:** Must implement `Task` or `Interaction`. Use `Instrumented` factory methods instead of `new`.
- **Assertions:** Use `Ensure.that(...)` or `Question` implementations. Never put `assertTrue` inside Tasks.
- **Step Definitions:** Reuse existing bindings under `com.learningmate.screenplay.tests.moodle.stepdefinitions`.

## 3. Data & Gherkin Rules
- For multi-field assertions, use 2-column vertical Cucumber Data Tables (`Map<String, String>`).

## UI MAP NAMING CONVENTION:
All static Target constants in UI classes MUST match their normalized UI display labels in UPPER_SNAKE_CASE.
Examples:
- "Email address" -> EMAIL_ADDRESS
- "City/town"     -> CITY_TOWN
- "First Name"    -> FIRST_NAME