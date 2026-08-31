# Screenplay Step Definition Generator

====================================================================
INPUT PARAMETERS (Fill this section only)
====================================================================
TARGET_UI_CLASS      : MoodleProfileUi
STEPDEF_PACKAGE      : com.learningmate.screenplay.apps.moodle.stepdefinitions
STEPDEF_CLASS_NAME   : ProfileStepDefinitions

MISSING_GHERKIN_STEP :
Then his profile details should match:
| Email address | student@moodle.a (Visible to other course participants) |
| Country       | Belgium          |
| City/town     | Brussels         |
| Timezone      | Australia/Perth  |
====================================================================

Generate ONLY the missing Step Definition Java method for MISSING_GHERKIN_STEP under STEPDEF_PACKAGE.

PROMPT CONSTRAINTS:

1. WORKSPACE INSPECTION:
    - Search STEPDEF_PACKAGE for STEPDEF_CLASS_NAME.java.
    - Inspect existing Task classes under `com.learningmate.screenplay.apps.moodle.task` and Question classes under `com.learningmate.screenplay.apps.moodle.question`.
    - Inspect TARGET_UI_CLASS to determine available Target constants (`public static final Target`).

2. ARCHITECTURAL RESOLUTION HIERARCHY (Check in exact order):
    - TIER 1 (Existing Tasks/Questions): If a Task/Question class exists for the step, invoke it.
    - TIER 2 (Static Explicit Target): If TARGET_UI_CLASS contains an explicit static Target matching the field name (e.g., `EMAIL_ADDRESS` for "Email address"), use `seeThat(Text.of(TARGET_UI_CLASS.EMAIL_ADDRESS), equalTo(expectedValue))`.
    - TIER 3 (Dynamic Target Fallback): If no explicit static Target exists for a specific key, fall back to `seeThat(Text.of(TARGET_UI_CLASS.PROFILE_FIELD.of(fieldName)), equalTo(expectedValue))`.
3. TASK / QUESTION CREATION RULE:
    - DO NOT invent, hallucinate, or propose non-existent Task or Question classes in this pass.
    - If a step definition requires complex logic not suitable for Tier 2 or Tier 3, generate a working inline Serenity implementation and add `// TODO: Consider refactoring into a Task class` above the method.

4. IMPLEMENTATION & ASSERTION PATTERN:
    - Data Table Handling: Map 2-column key-value Gherkin tables to `Map<String, String>`.
    - Loop Pattern: Iterate over entries using `.forEach((fieldName, expectedValue) -> ...)`.
    - Core Assertion Syntax:
      `theActorInTheSpotlight().should(seeThat(Text.of(TARGET_UI_CLASS.PROFILE_FIELD.of(fieldName)), equalTo(expectedValue)));`
    - STRICT DEPENDENCY RULE: Do NOT use `Ensure` assertions or import non-core modules. Rely purely on standard Serenity `seeThat(Text.of(...), equalTo(...))`.

5. REQUIRED IMPORTS:
   Include comments listing required imports for the generated code snippet:
    - `import net.serenitybdd.screenplay.questions.Text;`
    - `import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;`
    - `import static org.hamcrest.Matchers.equalTo;`
    - `import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;`

6. OUTPUT FORMAT (APPEND MODE):
    - IF STEPDEF_CLASS_NAME EXISTS: Output ONLY the missing Java step definition method wrapped inside clear copy-paste instructions for the existing file.
    - IF FILE IS NEW: Output the full Step Definition class structure including package, class declaration, and annotations.
    - Output plain Markdown text in the chat window only. Do NOT edit project files directly.