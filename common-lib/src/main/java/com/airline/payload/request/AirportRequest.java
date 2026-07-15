package com.airline.payload.request;

import com.airline.embeddable.Address;
import com.airline.embeddable.GeoCode;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZoneId;

@Getter
public class AirportRequest {

    @NotBlank(message = "IATA code is required")
    @Size(min = 3,max = 3, message = "IATA code must be exact 3 characters")
    private String iataCode;

    @NotBlank(message = "Airport name is required")
    private String name;

    @Valid
    private Address address;

    @Valid
    private GeoCode geoCode;

    @NotNull(message = "City ID is required")
    private Long cityId;

    private ZoneId timeZoneId;
}
