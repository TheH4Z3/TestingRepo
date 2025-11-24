package com.restful.booker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.rest.SerenityRest;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenIsValid implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        String token = SerenityRest.lastResponse().jsonPath().getString("token");
        assertThat(token)
                .as("Token should be present in the response")
                .isNotNull()
                .isNotEmpty()
                .matches("[A-Za-z0-9]+");

        return true;
    }

    public static TokenIsValid inTheResponse() {
        return new TokenIsValid();
    }
}
