#language: en
Feature: Booking Deletion

  Scenario: Valid delete returns 201
    Given I have a created booking ID
    When I delete the booking
    Then the response status should be 201

  Scenario: Delete without token returns 403
    Given I attempt to delete a booking without a token
    Then the response status should be 403

  Scenario: Delete non-existing ID returns proper status
    Given I have a non-existing booking ID
    When I delete the booking
    Then the response status should be 405 or 404
