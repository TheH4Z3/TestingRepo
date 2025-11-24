package com.restful.booker.tasks;

import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumePing implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(EnvConfig.get("BASE_PATH_PING"))
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );
    }

    public static ConsumePing consumePing(){
        return instrumented(ConsumePing.class);
    }
}
