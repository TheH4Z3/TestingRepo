package com.restful.booker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

import javax.swing.*;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingDetailsMatch implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        System.out.println("EXPECTED BOOKING = " + actor.recall("CREATED_BOOKING").toString());
        System.out.println("BOOKING RESPONSE = " + SerenityRest.lastResponse().jsonPath().getMap("$").toString());

        //Booking payload from creation time
        Map<String, Object> expectedBooking = actor.recall("CREATED_BOOKING");

        //GET /booking/{id} response → root object
        Map<String, Object> bookingResponse = SerenityRest.lastResponse().jsonPath().getMap("$");

        assertThat(bookingResponse)
                .as("Booking response should not be null")
                .isNotNull();

        //Field validations
        assertThat(bookingResponse.get("firstname"))
                .as("Firstname should match")
                .isEqualTo(expectedBooking.get("firstname"));

        assertThat(bookingResponse.get("lastname"))
                .as("Lastname should match")
                .isEqualTo(expectedBooking.get("lastname"));

        assertThat(bookingResponse.get("totalprice"))
                .as("Total price should match")
                .isEqualTo(expectedBooking.get("totalprice"));

        assertThat(bookingResponse.get("depositpaid"))
                .as("Deposit paid should match")
                .isEqualTo(expectedBooking.get("depositpaid"));

        assertThat(bookingResponse.get("additionalneeds"))
                .as("Additional needs should match")
                .isEqualTo(expectedBooking.get("additionalneeds"));


        Map<String, Object> expectedDates = (Map<String, Object>) expectedBooking.get("bookingdates");

        Map<String, Object> responseDates = (Map<String, Object>) bookingResponse.get("bookingdates");

        assertThat(responseDates.get("checkin"))
                .as("Check-in should match")
                .isEqualTo(expectedDates.get("checkin"));

        assertThat(responseDates.get("checkout"))
                .as("Checkout should match")
                .isEqualTo(expectedDates.get("checkout"));

        return true;
    }

    public static BookingDetailsMatch withTheCreatedData() {
        return new BookingDetailsMatch();
    }
}
