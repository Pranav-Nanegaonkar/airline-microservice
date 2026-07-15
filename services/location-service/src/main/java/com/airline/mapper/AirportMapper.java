package com.airline.mapper;

import com.airline.entity.Airport;
import com.airline.entity.City;
import com.airline.payload.request.AirportRequest;
import com.airline.payload.request.AirportUpdateRequest;
import com.airline.payload.response.AirportResponse;
import com.airline.repository.CityRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class AirportMapper {

    public static Airport toEntity(AirportRequest request) {
        if (request == null) {
            return null;
        }

        return Airport.builder()
                .iataCode(request.getIataCode())
                .name(request.getName())
                .address(request.getAddress())
                .geoCode(request.getGeoCode())
                .timeZoneId(request.getTimeZoneId())
                .build();
    }

    public static Airport updateAirport(Airport airport, AirportUpdateRequest request) {
        if (request == null) {
            return null;
        }
        if(request.getIataCode() != null) {
            airport.setIataCode(request.getIataCode());
        }
        if(request.getName() != null) {
            airport.setName(request.getName());
        }
        if(request.getAddress() != null) {
            airport.setAddress(request.getAddress());
        }
        if(request.getGeoCode() != null) {
            airport.setGeoCode(request.getGeoCode());
        }
        if (request.getTimeZoneId() != null) {
            airport.setTimeZoneId(request.getTimeZoneId());
        }
        return airport;
    }

    public static AirportResponse toResponse(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .address(airport.getAddress())
                .geoCode(airport.getGeoCode())
                .timeZoneId(airport.getTimeZoneId())
                .city(CityMapper.toResponse(airport.getCity()))
                .build();
    }
}
