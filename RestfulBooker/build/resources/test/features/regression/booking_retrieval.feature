#language: en
Feature: Booking Retrieval

  Scenario: Get booking by ID
    Given I have a created booking ID
    When I retrieve the booking by ID
    Then the response status should be 200
    And the booking details should be correct

  Scenario: Get non-existing booking returns 404
    Given I have a non-existing booking ID
    When I retrieve the booking by ID
    Then the response status should be 404

  Scenario: Filter by firstname
    When I filter bookings by firstname "John"
    Then the response should contain only bookings with firstname "John"

  Scenario: Filter by lastname
    When I filter bookings by lastname "Doe"
    Then the response should contain only bookings with lastname "Doe"

  Scenario: Filter by check-in date
    When I filter bookings by check-in date "2023-10-10"
    Then the response should contain only matching booking IDs
