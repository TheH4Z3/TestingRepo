package com.restful.booker.factory;

import com.github.javafaker.Faker;
import com.restful.booker.models.Booking;
import com.restful.booker.models.BookingDates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BookingFactory {

    private static final Faker faker = new Faker();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Booking generateBooking() {
        Date checkinDate = faker.date().future(30, TimeUnit.DAYS);
        Date checkoutDate = faker.date().future(5, TimeUnit.DAYS, checkinDate);

        BookingDates dates = BookingDates.builder()
                .checkin(sdf.format(checkinDate))
                .checkout(sdf.format(checkoutDate))
                .build();

        return Booking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 999))
                .depositpaid(faker.bool().bool())
                .bookingdates(dates)
                .additionalneeds(faker.lorem().sentence())
                .build();
    }
}
