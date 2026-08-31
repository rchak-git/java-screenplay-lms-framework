FRAMEWORK_ARCHITECTURE.md
1. Executive Summary & Design Philosophy
   This repository implements an enterprise-grade Moodle LMS test automation framework in Java, Serenity BDD, and Cucumber using the Screenplay Pattern.
   The framework is designed around behavior-driven, reusable building blocks:
   •
   Targets describe UI elements.
   •
   Tasks / Interactions express business actions.
   •
   Questions read state and verify outcomes.
   •
   Step Definitions stay thin and map Gherkin to Screenplay.
   The Screenplay approach is preferred over classic Page Object Models because it better supports:
   •
   Single Responsibility: each class does one thing well.
   •
   Open/Closed Principle: new behavior is added by composing Tasks/Questions rather than changing monolithic page objects.
   •
   Reuse: the same action and assertion components are shared across login, navigation, and profile flows.
   •
   Reporting: Serenity provides readable living documentation from business-level steps.
2. Core Architectural Layers
   UI / Target Layer
   The UI layer is centered on locator classes under com.learningmate.screenplay.apps.moodle.ui.
   Class
   Responsibility
   MoodleLoginUi
   Login form targets such as username, password, and submit button
   MoodleNavigationUi
   User menu and navigation dropdown targets
   MoodleProfileUi
   Profile summary/view-mode targets
   MoodleEditProfileUi
   Edit-profile form targets
   This project uses a dual locator strategy:
1.
Fixed locators for static controls
◦
Example: By.id("username"), By.id("password"), By.id("id_city"), By.id("id_country")
◦
Best for stable inputs, buttons, and selects.
2.
Parameterized dynamic targets for data-driven content
◦
Example: PROFILE_FIELD = Target.the("{0} profile field").locatedBy(...)
◦
Best for profile summary fields rendered as key-value pairs.
This split keeps locators resilient and avoids brittle duplication.
Task / Interaction Layer
Business actions are encapsulated in reusable Screenplay Tasks and Interactions under:
•
com.learningmate.screenplay.apps.moodle.task
•
com.learningmate.screenplay.core.action
Current examples:
•
LoginAs logs in with credentials.
•
NavigateMenu opens the user menu and selects a menu option.
•
TypeInto handles reliable text entry.
•
AutoCompleteSelect supports autocomplete field interaction.
•
Click, EnterText, OpenUrl, ClickRowAction, SelectFromOptions follow the same action-first design.
These classes keep Selenium/WebDriver mechanics hidden from step definitions.
Question / Assertion Layer
Assertions are implemented as Screenplay Questions that extract UI state cleanly:
•
MainPageLandingTitle reads the landing page title.
•
ReadCellByColumn reads table data by resolved column header.
•
Text.of(...) is used directly for profile assertions.
This layer is responsible for verifying page state without embedding locator logic in feature steps.
Feature / BDD Layer
Cucumber feature files live in src/test/resources/features/moodle.
Current feature coverage includes:
•
login.feature
•
student_profile.feature
These files map to step definitions in:
•
LoginStepDefinitions
•
MenuNavigationStepDefinitions
•
ProfileStepDefinitions
The framework already uses DataTables for structured verification:
•
login credentials as a 2-column table
•
profile field assertions as key/value tables
That makes the scenarios readable and scalable.
3. Repository Architecture Snapshot
   Area
   Package / File
   Purpose
   Runner
   MoodleIT
   JUnit + Serenity Cucumber execution entry point
   Config
   serenity.conf
   Browser, reporting, screenshots, and execution configuration
   UI
   apps.moodle.ui
   Moodle-specific locators
   Tasks
   apps.moodle.task
   Business actions
   Questions
   apps.moodle.question
   State extraction and assertions
   Core Actions
   core.action
   Reusable low-level Screenplay interactions
   Core Questions
   core.question.table
   Generic table-reading support
   Utilities
   core.util.DataReader
   YAML-backed test data loading
   Step Definitions
   tests.moodle.stepdefinitions
   Gherkin-to-Task/Question mapping
4. Current State
   Phase 1: Current State
   The framework currently provides:
   •
   Screenplay foundation
   •
   Serenity reporting integration
   •
   Component-based UI targets
   •
   Resilient dynamic locators
   •
   Login and profile verification workflows
   •
   DataTable-driven assertions
   •
   Browser execution through Serenity-managed WebDriver
   This is a good baseline for a maintainable Moodle automation framework.
5. POC Roadmap & Phased Strategy
   Phase 2: Immediate Next Steps
   Introduce a Typed Target / Control Delegation Pattern to handle mixed control types through a central strategy layer.
   This should cover:
   •
   text inputs
   •
   native <select> dropdowns
   •
   autocomplete controls
   •
   custom/React widgets
   •
   file upload elements
   Goal:
   •
   keep UI classes declarative
   •
   move control-specific behavior into a shared dispatcher
   •
   avoid if/else logic spreading across Tasks
   Phase 3: Future Enterprise State
   Evolve into a production-ready execution platform with:
   •
   Dockerized browser execution
   •
   Parallel grid support
   •
   Jenkins or GitHub Actions pipeline integration
   •
   Serenity living documentation published as build artifacts
   •
   environment-specific execution profiles
   •
   reusable data sets for regression and smoke runs
   Target outcome:
   •
   fast feedback locally
   •
   stable parallel CI execution
   •
   readable business reporting for stakeholders
6. Guidelines for Automation Engineers
   Declaring new Targets
   •
   Use By.id() first when a stable ID exists.
   •
   Use By.cssSelector() for compact stable selectors.
   •
   Use locatedBy(...) for parameterized dynamic content.
   •
   Prefer one dynamic target over many duplicated static targets when the DOM is key-value based.
   Writing Tasks
   •
   Put business intent in the Task name.
   •
   Keep UI mechanics inside the Task, not in steps.
   •
   Reuse existing actions like TypeInto, NavigateMenu, and AutoCompleteSelect.
   •
   Avoid direct WebDriver code unless the control type truly requires it.
   Writing Questions
   •
   Use Questions to read page state.
   •
   Keep assertions close to the UI model.
   •
   Use dynamic targets for repeated field verification.
   •
   Keep Questions deterministic and focused.
   Writing Step Definitions
   •
   Keep steps thin.
   •
   Map one Gherkin step to one business intent.
   •
   Use DataTable for structured data instead of hardcoded repeated steps.
   •
   Do not embed locator logic in step definitions.
   General Practices
   •
   Favor composition over duplication.
   •
   Keep locators in UI classes only.
   •
   Keep browser logic out of feature files.
   •
   Prefer readable business language in feature files.
   •
   Let Serenity reporting reflect actor intent, not technical implementation detail.
7. Design Principles Applied
   This framework aligns with:
   •
   SRP: one class, one responsibility
   •
   OCP: add new tasks/targets without rewriting existing flows
   •
   DRY: shared actions and questions
   •
   Separation of Concerns: UI, action, assertion, and BDD layers are isolated
   •
   Maintainability: Moodle page changes are localized to UI classes
8. Conclusion
   This codebase is already structured as a Screenplay-first automation framework with strong Serenity reporting and a clean BDD layer. The next major step is to standardize control handling so the framework can support complex Moodle UI components with the same consistency as today’s login and profile flows.