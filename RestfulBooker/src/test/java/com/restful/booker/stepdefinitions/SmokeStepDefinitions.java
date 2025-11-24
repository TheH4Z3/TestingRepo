package com.restful.booker.stepdefinitions;

import com.restful.booker.exceptions.ServiceException;
import com.restful.booker.questions.BookingDetailsMatch;
import com.restful.booker.questions.BookingIdIsValid;
import com.restful.booker.questions.StatusCode;
import com.restful.booker.questions.TokenIsValid;
import com.restful.booker.tasks.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.restful.booker.exceptions.ServiceException.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SmokeStepDefinitions {

    @Given("^I want to validate the API status$")
    public void iWantToValidateTheAPIStatus() {
    }


    @When("^I send a GET request to ping$")
    public void iSendAGETRequestToPing() {
        theActorInTheSpotlight().attemptsTo(
                ConsumePing.consumePing()
        );
    }


    @Then("^the response status should be (\\d+)$")
    public void theResponseStatusShouldBe(int statusCode) {
        theActorInTheSpotlight().should(
                seeThat(StatusCode.is(statusCode))
                        .orComplainWith(ServiceException.class, STATUS_CODE_IS_NOT_EXPECTED)
        );
    }


    @Given("^I have valid credentials$")
    public void iHaveValidCredentials() {
        theActorInTheSpotlight().attemptsTo(
                ConsumePing.consumePing()
        );
    }

    @When("^I send a POST request to auth$")
    public void iSendAPOSTRequestToAuth() {
        theActorInTheSpotlight().attemptsTo(
                CreateToken.createToken()
        );
    }

    @Then("^the response should contain a valid token$")
    public void theResponseShouldContainAValidToken() {
        theActorInTheSpotlight().should(
                seeThat(TokenIsValid.inTheResponse())
                        .orComplainWith(ServiceException.class, VALUE_SERVICE_IS_NOT_EXPECTED)
        );
    }


     @Given("^I need a valid booking payload$")
    public void iNeedAValidBookingPayload() {
        theActorInTheSpotlight().attemptsTo(
                ConsumePing.consumePing()
        );
    }

    @When("^I request a booking$")
    public void iRequestABooking() {
        theActorInTheSpotlight().attemptsTo(
                CreateBooking.createBooking()
        );
    }

    @Then("^a booking should be created successfully$")
    public void aBookingShouldBeCreatedSuccessfully() {
        theActorInTheSpotlight().should(
                seeThat(BookingIdIsValid.inTheResponse())
                        .orComplainWith(ServiceException.class, VALUE_SERVICE_IS_NOT_EXPECTED)
        );
    }



    @Given("^I created a booking ID$")
    public void iCreatedABookingID() {

    }

    @When("^I retrieve the booking by ID$")
    public void iRetrieveTheBookingByID() {
        theActorInTheSpotlight().attemptsTo(
                ConsumePing.consumePing(),
                CreateBooking.createBooking(),
                GetBooking.getBooking()
        );
    }

    @Then("^the response should match the created booking details$")
    public void theResponseShouldMatchTheCreatedBookingDetails() {
        theActorInTheSpotlight().should(
                seeThat(BookingDetailsMatch.withTheCreatedData())
                        .orComplainWith(ServiceException.class, VALUE_SERVICE_IS_NOT_EXPECTED)
        );
    }
}
