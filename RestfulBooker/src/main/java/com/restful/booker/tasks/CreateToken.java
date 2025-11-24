package com.restful.booker.tasks;

import com.restful.booker.factory.TokenFactory;
import com.restful.booker.models.TokenRequest;
import com.restful.booker.utils.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateToken implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        TokenRequest tokenPayload = TokenFactory.generateTokenPayload();
        actor.remember("TOKEN_PAYLOAD", tokenPayload);

        actor.attemptsTo(
                Post.to(EnvConfig.get("BASE_PATH_AUTH"))
                        .with(req -> req
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                                .body(tokenPayload)
                                .filter(new RequestLoggingFilter())
                                .filter(new ResponseLoggingFilter())
                        )
        );

        String token = SerenityRest.lastResponse().jsonPath().getString("token");
        actor.remember("TOKEN_VALUE", token);
    }

    public static CreateToken createToken() {
        return instrumented(CreateToken.class);
    }
}
