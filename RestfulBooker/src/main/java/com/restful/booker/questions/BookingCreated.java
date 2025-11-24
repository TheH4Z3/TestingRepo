package com.restful.booker.questions;

import com.restful.booker.models.Booking;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class BookingCreated implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        Booking sentBooking = actor.recall("CREATED_BOOKING_PAYLOAD");

        Booking returnedBooking = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("booking", Booking.class);

        return sentBooking.getFirstname().equals(returnedBooking.getFirstname())
            && sentBooking.getLastname().equals(returnedBooking.getLastname())
            && sentBooking.getTotalprice() == returnedBooking.getTotalprice()
            && sentBooking.isDepositpaid() == returnedBooking.isDepositpaid()
            && sentBooking.getBookingdates().getCheckin().equals(returnedBooking.getBookingdates().getCheckin())
            && sentBooking.getBookingdates().getCheckout().equals(returnedBooking.getBookingdates().getCheckout());
    }

    public static BookingCreated isReturned() {
        return new BookingCreated();
    }
}
