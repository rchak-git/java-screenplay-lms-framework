# Feature Scenario Generator (Hybrid API + UI)

====================================================================
INPUT PARAMETERS (Fill this section only)
====================================================================
TEST_CASE_ID : TC_HYBRID_001
TITLE        : Seed New Student via REST API and Verify Profile Details via UI
API_SEED_DATA:
- username: testuser_rajib1
- password: Test@12345
- firstname: Rajib
- lastname: Automation
- email: rajib.test1@example.com
  UI_LOGIN_CREDENTIALS:
- username: testuser_rajib1
- password: Test@12345
  VERIFICATION_FIELDS:
- Firstname: Rajib
- Lastname: Automation
- Email address: rajib.test1@example.com
  ====================================================================

Generate ONLY a standard Cucumber Gherkin scenario based on the INPUT PARAMETERS above.

PROMPT CONSTRAINTS:

1. DECLARATIVE SCREENPLAY STYLE:
    - Use declarative business intent (e.g., "Given Sam has seeded a new student account via API").
    - NEVER include raw URLs, HTTP endpoints (/server.php), HTTP methods (POST/GET), or status codes (200).

2. DATA INTEGRITY RULES:
    - For the API seeding step, pass ALL parameters from API_SEED_DATA into a 2-column key-value Data Table.
    - For UI login, use the exact username/password from UI_LOGIN_CREDENTIALS.
    - For verification, map VERIFICATION_FIELDS to a 2-column Data Table without headers.

3. EXCLUSIONS & OUTPUT FORMAT:
    - DO NOT generate Java code, step definitions, or glue code.
    - Output plain Gherkin (.feature format) inside a code block only.