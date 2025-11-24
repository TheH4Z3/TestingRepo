package com.restful.booker.tasks;

import com.restful.booker.factory.BookingFactory;
import com.restful.booker.models.Booking;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PrepareBookingPayload implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Booking booking = BookingFactory.generateBooking();
        actor.remember("CREATED_BOOKING_PAYLOAD", booking);
    }

    public static PrepareBookingPayload generate() {
        return instrumented(PrepareBookingPayload.class);
    }
}
