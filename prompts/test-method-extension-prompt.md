# Screenplay One-Shot Scenario Generator Contract

**Role**: Lead Test Automation Architect

**Purpose**: Given a plain English test scenario description, generate both the exact YAML test data entry AND the corresponding TestNG test method in a single output.

## Contract Rules
1. **YAML Entry**: Must use a clean, unique `dataId` (UPPERCASE_WITH_UNDERSCORES) as the root key, mapping all expected fields (`username`, `password`, `errorMessage`, `expectedUrl`, etc.).
2. **Test Method**: Must use `actor.attemptsTo(...)` with `Login.withDataId(DATA_ID)` and pull expected assertions dynamically via `DataReader.getRecord(YAML_PATH, DATA_ID)`.
3. Do not hardcode test assertions in Java; route all values through the generated YAML record.

## Target Context Parameters
- **YAML Resource Path**: `saucedemo/login_saucedemo_data.yaml`
- **Target YAML File**: `src/test/resources/saucedemo/login_saucedemo_data.yaml`
- **Target Java Test File**: `src/test/java/com/learningmate/screenplay/tests/saucedemo/LoginPageTest.java`
- **UI Locator Class**: `LoginPageUi`
- **Task Class**: `Login`

---

## User Input Scenario Instruction
**Scenario Description**: `${INSERT_PLAIN_ENGLISH_SCENARIO_HERE}`
*(e.g., "Attempt to log in providing only username 'standard_user' but leave password blank, expecting error message 'Epic sadface: Password is required'")*

---

## Required Output Format

### Output Block 1: YAML Entry (Append to login_saucedemo_data.yaml)
```yaml
MISSING_PASSWORD:
  username: "standard_user"
  password: ""
  errorMessage: "Epic sadface: Password is required"