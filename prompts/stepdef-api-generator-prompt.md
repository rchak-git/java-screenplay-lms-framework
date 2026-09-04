# Screenplay Step Definition Generator (API Focus)

====================================================================
INPUT PARAMETERS (Fill this section only)
====================================================================
STEPDEF_PACKAGE      : com.learningmate.screenplay.apps.moodle.stepdefinitions
STEPDEF_CLASS_NAME   : ApiStepDefinitions

MISSING_GHERKIN_STEP :
Given "Rajib" has seeded a new student account via API:
| username  | testuser_rajib1         |
| password  | Test@12345             |
| firstname | Rajib                   |
| lastname  | Automation              |
| email     | rajib.test1@example.com |
====================================================================

Generate ONLY the missing Step Definition Java method for MISSING_GHERKIN_STEP under STEPDEF_PACKAGE.

PROMPT CONSTRAINTS:

1. WORKSPACE INSPECTION:
    - Search STEPDEF_PACKAGE for STEPDEF_CLASS_NAME.java.
    - Inspect existing Task classes under `com.learningmate.screenplay.apps.moodle.tasks.api`.

2. ARCHITECTURAL RESOLUTION HIERARCHY (API Step):
    - TIER 1 (Existing Task): If an API Task class exists (e.g., `SeedStudentUser.withDetails(...)`), invoke it using `OnStage.theActorCalled(actorName).attemptsTo(...)`.
    - TIER 2 (Inline REST Assured Task Fallback): If no API Task class exists, generate a working inline Screenplay `Task` or `CallAnApi` interaction call, and add `// TODO: Refactor inline API logic into a dedicated Task class` above the method.

3. DATA TABLE HANDLING:
    - Map 2-column key-value Gherkin tables to `Map<String, String>`.
    - Pass the extracted `Map<String, String>` into the API seeding task execution.

4. ACTOR MANAGEMENT:
    - Initialize or retrieve the actor using `OnStage.theActorCalled(actorName)`.

5. REQUIRED IMPORTS:
    - `import io.cucumber.java.en.Given;`
    - `import net.serenitybdd.screenplay.actors.OnStage;`
    - `import java.util.Map;`

6. OUTPUT FORMAT (APPEND MODE):
    - IF STEPDEF_CLASS_NAME EXISTS: Output ONLY the missing Java step definition method wrapped inside clear copy-paste instructions for the existing file.
    - IF FILE IS NEW: Output the full Step Definition class structure including package, class declaration, and annotations.
    - Output plain Markdown text in the chat window only. Do NOT edit project files directly.