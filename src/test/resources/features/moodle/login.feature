@moodle
Feature: Moodle User Login

  Scenario Outline: Successful user login with valid credentials
    Given "Rajib" is on the Moodle login page
    When he logs in with username "<username>" and password "<password>"
    Then he should see the Title of Page "<main_page_title>"

    Examples:
      | username    | password     | main_page_title        |
      | student  | sandbox24 | Moodle 5.2 sandbox demo |