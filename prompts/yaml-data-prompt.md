# Screenplay YAML Test Data Contract

**Role**: Test Automation Engineer

**Purpose**: Generate a clean YAML test data file matching the framework's record structure for the Payment Form.

## Rules
- Root elements MUST be unique `dataId` keys in UPPER_SNAKE_CASE (e.g., `VALID_PAYMENT`, `PENDING_PAYMENT`).
- Property names MUST strictly match the framework keys: `customerName`, `amount`, and `scenario`.
- DO NOT wrap records in outer list nodes or extra wrapper keys.

## Input Parameters
- **Target File Path**: src/test/resources/payments/
- **File Name**: payment_form_data.yaml
- **Feature Name**: PaymentForm

## Scenarios to Generate
1. `VALID_PAYMENT`: A standard successful transaction (`customerName`: "Ravi", `amount`: "100.00", `scenario`: "SUCCESS").
2. `PENDING_PAYMENT`: A standard pending transaction (`customerName`: "Rajib", `amount`: "2000.00", `scenario`: "PENDING").
3. `HIGH_VALUE_PAYMENT`: An edge-case payment with a large amount (e.g., 999999.99) and "SUCCESS" scenario.

## Canonical Blueprint
VALID_USER:
username: "standard_user"
password: "secret_sauce"
expectedUrl: "https://www.saucedemo.com/inventory.html"

LOCKED_USER:
username: "locked_out_user"
password: "secret_sauce"
errorMessage: "Epic sadface: Sorry, this user has been locked out."

## Output Instruction
Create the file `${FILE_NAME}` inside directory `${TARGET_PATH}` using the generated content matching the blueprint structure.