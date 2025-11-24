#language: en
Feature: API Smoke Tests
  Purpose: Confirm the API is alive and core endpoints work.

  @Ping
  Scenario: GET ping returns 201
    Given I want to validate the API status
    When I send a GET request to ping
    Then the response status should be 201

  @Auth
  Scenario: POST auth returns a valid token
    Given I have valid credentials
    When I send a POST request to auth
    Then the response status should be 200
    And the response should contain a valid token

@CreateBooking
  Scenario: POST booking create
    Given I need a valid booking payload
    When I request a booking
    Then the response status should be 200
    And a booking should be created successfully

  @BookingGet
  Scenario: GET booking id retrieves the created booking
    Given I created a booking ID
    When I retrieve the booking by ID
    Then the response status should be 200
    And the response should match the created booking details