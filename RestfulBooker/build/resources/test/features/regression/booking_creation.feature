#language: en
Feature: Booking Creation

  Scenario: Happy path - valid booking creates successfully
    Given I have a valid booking payload
    When I create a booking
    Then the response status should be 200
    And the booking should be created successfully

  Scenario: Missing required fields returns 400
    Given I have a booking payload with missing fields
    When I create a booking
    Then the response status should be 400

  Scenario: Invalid date format returns 400
    Given I have a booking payload with invalid date format
    When I create a booking
    Then the response status should be 400

  Scenario: Accept JSON format
    Given I have a valid booking payload in JSON
    When I create a booking
    Then the response status should be 200

  Scenario: Accept XML format
    Given I have a valid booking payload in XML
    When I create a booking
    Then the response status should be 200
