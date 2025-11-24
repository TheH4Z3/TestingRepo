package com.restful.booker.tasks;

import com.restful.booker.factory.BookingFactory;
import com.restful.booker.models.Booking;
import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateBooking implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        Integer bookingId = actor.recall("BOOKING_ID");

        //Generate updated booking payload
        Booking updatedBooking = BookingFactory.generateBooking();
        actor.remember("UPDATED_BOOKING_PAYLOAD", updatedBooking);

        actor.attemptsTo(
                Put.to(EnvConfig.get("BASE_PATH_BOOKING") + "/" + bookingId)
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .header("Accept", ContentType.JSON)
                                .body(updatedBooking)
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );

        actor.remember("LAST_UPDATE_RESPONSE", SerenityRest.lastResponse());
    }

    public static UpdateBooking updateBooking() {
        return instrumented(UpdateBooking.class);
    }
}
