#language: en
Feature: Non-functional Testing

  Scenario: Response time should be under 2 seconds
    When I send a GET request to "/booking"
    Then the response time should be less than 2000 ms

  Scenario: Validate JSON schema
    Given I have a JSON schema for booking responses
    When I retrieve a booking by ID
    Then the response should match the defined schema
