#language: en
Feature: Booking Update (PUT)

  Scenario: Valid update returns updated values
    Given I have a created booking ID
    And I have valid update data
    When I update the booking
    Then the response status should be 200
    And the booking should reflect the updated values

  Scenario: Missing token returns 403
    Given I have a created booking ID
    And I update without sending a token
    Then the response status should be 403

  Scenario: Invalid token returns 403
    Given I have a created booking ID
    And I update with an invalid token
    Then the response status should be 403
