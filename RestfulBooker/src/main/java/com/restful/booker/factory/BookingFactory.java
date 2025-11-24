package com.restful.booker.factory;

import com.github.javafaker.Faker;
import com.restful.booker.models.Booking;
import com.restful.booker.models.BookingDates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingFactory {

    public static Booking generateBooking() {
        Faker faker = new Faker();

        Date checkin = faker.date().future(30, TimeUnit.DAYS);
        Date checkout = faker.date().future(5, TimeUnit.DAYS, checkin);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return Booking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 999))
                .depositpaid(faker.bool().bool())
                .bookingdates(
                        BookingDates.builder()
                                .checkin(sdf.format(checkin))
                                .checkout(sdf.format(checkout))
                                .build()
                )
                .additionalneeds(faker.lorem().sentence())
                .build();
    }
}
