package com.restful.booker.questions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class StatusCode implements Question<Boolean> {

    private final int code;
    public StatusCode(int statusCode) {
        this.code = statusCode;
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse("response status code",
                        response -> response
                                .statusCode(code)
                )
        );
        return true;
    }
    public static StatusCode is(int statusCode){
        return new StatusCode(statusCode);
    }

}
