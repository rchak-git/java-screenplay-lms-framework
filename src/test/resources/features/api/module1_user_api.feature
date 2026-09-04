Feature: ReqRes User Management API

  @api
  Scenario: Get details of a single user
    Given "Sam" is an API client targeting "https://reqres.in"
    When he sends a GET request to "/api/users/2"
    Then the response status code should be 200
    And the user's first name should be "Janet"