# Screenplay YAML Test Data Contract

**Role**: Test Automation Engineer

**Purpose**: Generate a clean YAML test data file matching the framework's record structure.

## Rules
- Root elements MUST be unique `dataId` keys (e.g., `VALID_USER`, `LOCKED_USER`).
- Keep property names lowercase (`username`, `password`, `errorMessage`, `expectedUrl`).
- DO NOT wrap records in outer list nodes or extra wrapper keys.

## Input Parameters
- **Target File Path**: `${TARGET_PATH}` (e.g., `src/test/resources/saucedemo/`)
- **File Name**: `${FILE_NAME}` (e.g., `login_saucedemo_data.yaml`)
- **Feature Name**: `${FEATURE_NAME}` (e.g., `SauceDemo Login`)
- **Scenarios**:
  1. Valid login with standard credentials
  2. Locked out user attempt with error assertion
  3. Invalid credentials attempt with error assertion

## Output Instruction
Create the file `${FILE_NAME}` inside directory `${TARGET_PATH}` using the content below:

## Canonical Blueprint
VALID_USER:
username: "standard_user"
password: "secret_sauce"
expectedUrl: "https://www.saucedemo.com/inventory.html"

LOCKED_USER:
username: "locked_out_user"
password: "secret_sauce"
errorMessage: "Epic sadface: Sorry, this user has been locked out."

INVALID_USER:
username: "invalid_user"
password: "wrong_password"
errorMessage: "Epic sadface: Username and password do not match any user in this service"