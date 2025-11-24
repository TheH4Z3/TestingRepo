#language: en
Feature: Partial Booking Update (PATCH)

  Scenario: Partial update changes only specific fields
    Given I have a created booking ID
    And I have partial update data
    When I partially update the booking
    Then the response status should be 200
    And only the provided fields should be updated

  Scenario: Fields not provided remain unchanged
    Given I have a created booking ID
    When I partially update the booking with a single field
    Then the missing fields should remain unchanged
