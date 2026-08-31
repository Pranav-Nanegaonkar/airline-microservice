package com.airline.core.mapper;

import com.airline.commonlib.embeddable.Support;
import com.airline.commonlib.enums.AirlineStatus;
import com.airline.commonlib.payload.request.AirlineRequest;
import com.airline.commonlib.payload.request.AirlineUpdateRequest;
import com.airline.commonlib.payload.response.AirlineResponse;
import com.airline.core.entity.Airline;
import lombok.*;


public class AirlineMapper {

    public static Airline toEntity(AirlineRequest request, Long ownerId) {

        if (request == null) {
            return null;
        }

        Airline airline = Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .name(request.getName())
                .alias(request.getAlias())
                .ownerId(ownerId)
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus() != null ? request.getStatus() : AirlineStatus.ACTIVE)
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                .build();

        if (request.getSupportEmail() != null
                || request.getSupportPhone() != null
                || request.getSupportHours() != null) {
            airline.setSupport(
                    Support.builder()
                            .email(request.getSupportEmail())
                            .phone(request.getSupportPhone())
                            .hours(request.getSupportHours())
                            .build()
            );
        }
        return airline;
    }

    public static Airline updateAirline(Airline airline, AirlineUpdateRequest request) {
        if (request.getIataCode() != null) {
            airline.setIataCode(request.getIataCode());
        }
        if (request.getIcaoCode() != null) {
            airline.setIcaoCode(request.getIcaoCode());
        }
        if (request.getName() != null) {
            airline.setName(request.getName());
        }
        if (request.getAlias() != null) {
            airline.setAlias(request.getAlias());
        }
        if (request.getLogoUrl() != null) {
            airline.setLogoUrl(request.getLogoUrl());
        }
        if (request.getWebsite() != null) {
            airline.setWebsite(request.getWebsite());
        }
        if (request.getStatus() != null) {
            airline.setStatus(request.getStatus());
        }
        if (request.getAlliance() != null) {
            airline.setAlliance(request.getAlliance());
        }
        if (request.getHeadquartersCityId() != null) {
            airline.setHeadquartersCityId(request.getHeadquartersCityId());
        }
        if (airline.getSupport() == null) {
            airline.setSupport(new Support());
        }
        if (request.getSupportEmail() != null) {
            airline.getSupport().setEmail(request.getSupportEmail());
        }
        if (request.getSupportPhone() != null) {
            airline.getSupport().setPhone(request.getSupportPhone());
        }
        if (request.getSupportHours() != null) {
            airline.getSupport().setHours(request.getSupportHours());
        }

        return airline;
    }

    public static AirlineResponse toResponse(Airline airline) {
        if (airline == null) {
            return null;
        }

        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .headquartersCityId(airline.getHeadquartersCityId())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .build();

    }

}
