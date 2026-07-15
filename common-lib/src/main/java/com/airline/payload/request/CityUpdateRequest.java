package com.airline.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
public class CityUpdateRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 10)
    private String cityCode;

    @Size(max = 5)
    private String countryCode;

    @Size(max = 100)
    private String countryName;

    @Size(max = 10)
    private String regionCode;

    @Size(max = 10)
    private String timeZoneId;
}
