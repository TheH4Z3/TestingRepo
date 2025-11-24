package com.restful.booker.questions;

import com.restful.booker.models.Booking;
import com.restful.booker.models.BookingDates;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingDetailsMatch implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        Booking expected = actor.recall("CREATED_BOOKING_PAYLOAD");
        if (expected == null) {
            throw new IllegalStateException("CREATED_BOOKING_PAYLOAD not found in actor memory");
        }

        Booking actual = SerenityRest.lastResponse().as(Booking.class);
        assertThat(actual).as("GET booking response should not be null").isNotNull();


        //Compare simple fields
        assertThat(actual.getFirstname()).as("firstname").isEqualTo(expected.getFirstname());
        assertThat(actual.getLastname()).as("lastname").isEqualTo(expected.getLastname());
        assertThat(actual.getTotalprice()).as("totalprice").isEqualTo(expected.getTotalprice());
        assertThat(actual.isDepositpaid()).as("depositpaid").isEqualTo(expected.isDepositpaid());
        assertThat(actual.getAdditionalneeds()).as("additionalneeds").isEqualTo(expected.getAdditionalneeds());

        //Compare nested dates
        BookingDates expectedDates = expected.getBookingdates();
        BookingDates actualDates = actual.getBookingdates();

        assertThat(actualDates.getCheckin()).as("checkin").isEqualTo(expectedDates.getCheckin());
        assertThat(actualDates.getCheckout()).as("checkout").isEqualTo(expectedDates.getCheckout());

        return true;
    }

    public static BookingDetailsMatch withTheCreatedData() {
        return new BookingDetailsMatch();
    }
}
