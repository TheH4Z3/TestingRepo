#language: en
Feature: Negative Testing

  Scenario: Invalid HTTP verb on /booking
    When I send a DELETE request to "/booking"
    Then the response status should be 405

  Scenario: Boundary testing for dates
    Given I have a booking payload with invalid boundary dates
    When I create a booking
    Then the response status should be 400

  Scenario: Boundary testing for prices
    Given I have a booking payload with price "-1"
    When I create a booking
    Then the response status should be 400

  Scenario: Invalid JSON body
    Given I send an invalid JSON payload
    When I create a booking
    Then the response status should be 400
