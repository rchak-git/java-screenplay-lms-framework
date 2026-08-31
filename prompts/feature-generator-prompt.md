# Feature Scenario Generator

====================================================================
INPUT PARAMETERS (Fill this section only)
====================================================================
TEST_CASE_ID : TC_PROFILE_02
TITLE        : Verify Student Profile Details on LMS
STEPS        :
1. Login to Moodle as student (username: student, password: sandbox24).
2. Navigate to User Profile page.
3. Verify fields match:
   - Email address: student@moodle.a
   - Country: Belgium
   - City/town: Brussels
   - Timezone: Australia/Perth
     ====================================================================

Generate ONLY a standard Cucumber Gherkin scenario based on the INPUT PARAMETERS above.

PROMPT CONSTRAINTS:

1. WORKSPACE STEP REUSE:
   - Search the existing `src/test/resources/features` and `stepdefinitions` package.
   - Strictly reuse existing `@Given`, `@When`, and `@Then` phrase patterns to maintain step definition compatibility across scenarios.

2. SCENARIO STRUCTURE:
   - Scenario Title format: `Scenario: TEST_CASE_ID - TITLE`
   - Use declarative Screenplay-style Gherkin (focus on user intent, avoid low-level UI clicks/types in scenario text).
   - Pass credentials and complex field verifications using Cucumber Data Tables.

3. DATA TABLE FORMATTING RULES:
   - For credentials/key-value inputs: Format as a 2-column key-value table (e.g., `| username | password |`).
   - For multi-field assertions: Format as a 2-column key-value Data Table WITHOUT headers.

4. EXCLUSIONS & OUTPUT FORMAT:
   - DO NOT generate Java step definition code, UI targets, or Task classes.
   - Output plain Markdown Gherkin text (`.feature` format) in chat window only.
   - Do NOT edit or create project files directly.