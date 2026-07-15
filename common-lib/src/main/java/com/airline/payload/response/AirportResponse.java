package com.airline.payload.response;

import com.airline.embeddable.Address;
import com.airline.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
    private Long id;
    private String iataCode;
    private String name;
    private Address address;
    private GeoCode geoCode;
    private ZoneId timeZoneId;
    private CityResponse city;
}
