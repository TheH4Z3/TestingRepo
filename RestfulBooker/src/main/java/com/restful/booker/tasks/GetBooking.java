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
        actor.attemptsTo(
                Get.resource(EnvConfig.get("BASE_PATH_BOOKING") + "/" + actor.recall("BOOKING_ID"))
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .header("Accept", ContentType.JSON)
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );
    }

    public static GetBooking getBooking(){
        return instrumented(GetBooking.class);
    }
}
