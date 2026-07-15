package com.airline.payload.request;

import com.airline.embeddable.Address;
import com.airline.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;

@Getter
public class AirportUpdateRequest {
    @Size(min = 3,max = 3, message = "IATA code must be exact 3 characters")
    private String iataCode;

    private String name;

    @Valid
    private Address address;

    @Valid
    private GeoCode geoCode;

    private Long cityId;

    private ZoneId timeZoneId;
}
