package com.restful.booker.models;

import com.restful.booker.models.BookingDates;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
