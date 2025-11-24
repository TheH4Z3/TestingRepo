package com.restful.booker.tasks;

import com.restful.booker.interactions.ModifyBody;
import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateBooking implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(EnvConfig.get("BASE_PATH_BOOKING"))
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .header("Accept", ContentType.JSON)
                                .body(ModifyBody.reqCreateBooking())
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );
    }

    public static UpdateBooking createBooking() {
        return instrumented(UpdateBooking.class);
    }
}