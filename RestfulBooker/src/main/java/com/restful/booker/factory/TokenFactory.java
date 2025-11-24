package com.restful.booker.factory;

import com.restful.booker.models.TokenRequest;
import com.restful.booker.utils.EnvConfig;

public class TokenFactory {
    public static TokenRequest generateTokenPayload() {
        return TokenRequest.builder()
                .username(EnvConfig.get("AUTH_NAME"))
                .password(EnvConfig.get("AUTH_PASSWORD"))
                .build();
    }
}