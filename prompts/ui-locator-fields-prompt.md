Act as a Senior Serenity BDD Screenplay Architect.

TASK:
Generate standalone Serenity Screenplay `Target` locators for the action elements (buttons, links, inputs) provided in the HTML snippet below, and append them to `TARGET_UI_CLASS`.

INPUT:
- TARGET_UI_CLASS: MoodleProfileUi
- HTML_SNIPPET:
  <a href="https://sandbox.moodledemo.net/user/edit.php?id=4&amp;returnto=profile">Edit profile</a>

RULES:====================================================================

Generate or update `TARGET_UI_CLASS.java` under package `TARGET_PACKAGE` using Serenity BDD Screenplay `Target` locators based on the INPUT PARAMETERS above.

PROMPT CONSTRAINTS:

1. WORKSPACE INSPECTION & VERIFICATION:
   - Search the workspace and inspect `TARGET_UI_CLASS.java` directly.
   - DO NOT assume a target exists. If the literal constant definition (e.g., `public static final Target PROFILE_FIELD`) is not physically present in `TARGET_UI_CLASS.java`, treat it as MISSING.
   - Do NOT rewrite or modify existing Target declarations inside the file.

2. DYNAMIC VS STATIC TARGET STRATEGY:
   - DYNAMIC PREFERENCE: If scenario fields share a standard key-value layout (e.g., `<dl>/<dt>/<dd>` or form labels), generate ONLY a single parameterized Target constant:
     `public static final Target PROFILE_FIELD = Target.the("{0} profile field").locatedBy("//dt[contains(normalize-space(), '{0}')]/following-sibling::dd[1]");`
   - STRICT ANTI-PATTERN RULE (No Pre-Evaluated Duplicates): Do NOT generate pre-evaluated static targets using `.of(...)` (e.g., do NOT generate `public static final Target EMAIL_ADDRESS = PROFILE_FIELD.of("Email address");`).
   - NO DUPLICATES: Do NOT generate individual static targets for fields covered by a dynamic Target constant.

3. LOCATOR RESILIENCE CONSTRAINTS:
   - XPath Text Matching: Use `contains(normalize-space(), '{0}')` instead of exact equality `normalize-space()='{0}'` or raw `text()` to account for trailing colons or hidden whitespace.
   - Standalone targets must prefer `By.id()`, `By.cssSelector()`, or resilient unique XPaths.

4. OUTPUT FORMAT (APPEND MODE):
   - IF FILE EXISTS: Output ONLY the new missing `public static final Target` code snippets wrapped inside clear copy-paste instructions. Do NOT print the surrounding class structure.
   - IF FILE IS NEW: Output the full class definition including package, imports, and private default constructor.
   - Output plain Markdown text in the chat window only. Do NOT edit project files directly.