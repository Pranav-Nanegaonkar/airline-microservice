package com.airline.commonlib.payload.request;

import com.airline.commonlib.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AirlineUpdateRequest {

    @Size(min = 3, max = 3, message = "IATA code must have 3 letters")
    private String iataCode;
    @Size(min = 4, max = 4, message = "ICAO code must have 4 letters")
    private String icaoCode;
    private String name;
    private String alias;
    private String logoUrl;
    private String website;
    private AirlineStatus status;
    private String alliance;
    private Long headquartersCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;
}
