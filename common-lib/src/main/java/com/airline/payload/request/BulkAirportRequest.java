package com.airline.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class BulkAirportRequest {

    @NotEmpty(message = "Airport list cannot be empty")
    @Valid
    private List<AirportRequest> airports;
}
