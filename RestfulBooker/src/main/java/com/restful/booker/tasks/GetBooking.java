package com.restful.booker.tasks;

import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetBooking implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        Integer bookingId = actor.recall("BOOKING_ID");
        if (bookingId == null) {
            throw new IllegalStateException("BOOKING_ID not found in actor memory");
        }

        actor.attemptsTo(
                Get.resource(EnvConfig.get("BASE_PATH_BOOKING") + "/" + bookingId)
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );
    }

    public static GetBooking getBooking() {
        return instrumented(GetBooking.class);
    }
}
