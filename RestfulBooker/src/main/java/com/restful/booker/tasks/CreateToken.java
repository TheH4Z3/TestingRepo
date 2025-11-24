package com.restful.booker.tasks;

import com.restful.booker.interactions.ModifyBody;
import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateToken implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(EnvConfig.get("BASE_PATH_AUTH"))
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .body(ModifyBody.reqCreateToken())
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );
    }

    public static CreateToken createToken(){
        return instrumented(CreateToken.class);
    }
}
