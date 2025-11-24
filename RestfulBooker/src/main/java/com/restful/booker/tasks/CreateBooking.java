package com.restful.booker.tasks;

import com.restful.booker.factory.BookingFactory;
import com.restful.booker.models.Booking;
import com.restful.booker.utils.EnvConfig;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBooking implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        Booking bookingPayload = BookingFactory.generateBooking();

        actor.remember("CREATED_BOOKING_PAYLOAD", bookingPayload);

        actor.attemptsTo(
                Post.to(EnvConfig.get("BASE_PATH_BOOKING"))
                        .with(req -> req
                                .contentType(ContentType.JSON)
                                .header("Accept", ContentType.JSON)
                                .body(bookingPayload)
                        )
        );

        Integer bookingId = SerenityRest.lastResponse().jsonPath().getInt("bookingid");

        actor.remember("BOOKING_ID", bookingId);
    }

    public static CreateBooking createBooking() {
        return instrumented(CreateBooking.class);
    }
}
