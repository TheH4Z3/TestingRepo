#language: en
Feature: Authentication API

  Scenario: Should generate a valid token
    Given I have valid credentials
    When I send a POST request to "/auth"
    Then the response status should be 200
    And the response should contain a valid token

  Scenario: Should reject invalid credentials
    Given I have invalid credentials
    When I send a POST request to "/auth"
    Then the response status should be 200
    And the response should indicate login failure

  Scenario: Token should match JWT structure
    Given I have a valid token
    When I decode the token
    Then the token should contain the required claims
