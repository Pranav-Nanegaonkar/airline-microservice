package com.airline.payload.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
public class BulkCityRequest {

    @NotEmpty(message = "City list cannot be empty")
    @Valid
    private List<CityRequest> cities;
}