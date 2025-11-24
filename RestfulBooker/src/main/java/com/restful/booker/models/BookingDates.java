package com.restful.booker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {
    @JsonProperty("checkin")
    private String checkin;
    @JsonProperty("checkout")
    private String checkout;
}
