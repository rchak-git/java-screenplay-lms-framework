# Screenplay Test Class Generator Contract

**Role**: Lead Test Automation Architect

**Purpose**: Generate a TestNG unit test class that executes Screenplay Tasks for the Payment Form using dynamic YAML data.

## Rules
- MUST instantiate `Actor actor = Actor.named("Rajib")` and equip with `BrowseTheWeb` in `@BeforeMethod`.
- MUST navigate to "http://localhost:8080/payment-form" in `@BeforeMethod`.
- MUST generate a separate `@Test` method for EACH scenario key listed below.
- Tasks MUST be invoked via `actor.attemptsTo(CreatePayment.usingFile(yamlPath, DATA_ID))`.

## Input Parameters
- **Target Directory Path**: src/test/java/com/learningmate/screenplay/tests/payments
- **File Name**: PaymentFormTest.java
- **Package Name**: com.learningmate.screenplay.apps.payments.test
- **YAML Data File Path**: src/test/resources/payments/payment_form_data.yaml

## Scenarios to Generate Test Methods For
1. `VALID_PAYMENT` -> `testValidPaymentSubmission`
2. `PENDING_PAYMENT` -> `testPendingPaymentSubmission`
3. `HIGH_VALUE_PAYMENT` -> `testHighValuePaymentSubmission`

## Output Instruction
Generate the `PaymentFormTest.java` class inside `${TARGET_DIR_PATH}` implementing all `@BeforeMethod` setup and test methods specified in the scenarios list above.