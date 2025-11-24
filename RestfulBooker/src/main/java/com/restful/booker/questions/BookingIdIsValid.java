package com.restful.booker.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingIdIsValid implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        Integer bookingId = SerenityRest.lastResponse().jsonPath().getInt("bookingid");

        assertThat(bookingId)
                .as("Booking ID should be present in the response")
                .isNotNull()
                .isPositive();

        if (actor.recall("BOOKING_ID") == null) {
            actor.remember("BOOKING_ID", bookingId);
        }

        return true;
    }

    public static BookingIdIsValid inTheResponse() {
        return new BookingIdIsValid();
    }
}
