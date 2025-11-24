package com.restful.booker.utils;

public enum Route {

    AUTH_JSON("src/test/resources/data/Auth.json"),
    BOOKING_JSON("src/test/resources/data/Booking.json");


    private final String routeFile;
    Route(String routeFile) {
        this.routeFile = routeFile;
    }

    public String getRouteFile() {
        return routeFile;
    }
}
