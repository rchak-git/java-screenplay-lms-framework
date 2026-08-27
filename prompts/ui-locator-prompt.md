# Screenplay UI Target Generator

Generate `MoodleLoginUi.java` under package `com.learningmate.screenplay.apps.moodle.ui` using Serenity BDD Screenplay pattern (`public static final Target`, `SCREAMING_SNAKE_CASE`, `By` locators).

**Target Elements to Generate:**
- Username
- Password
- Log in
- Welcome back
- [Optional] Table / Dynamic Row mapping: Generate parameterized targets accepting key parameters (e.g., row text or cell content) using `.locatedBy("//table[...]...")`.

**Locator Rules:**
1. Prefer `By.id()` or unique stable attributes (`name`, `data-testid`).
2. For tables, build dynamic XPaths based on anchor column values (e.g., finding a row by row name in Column 1 and referencing an action button in Column 3: `//table//tr[td[normalize-space()='{0}']]//button`).

**HTML DOM Snippet:**
[Paste HTML here]