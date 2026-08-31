package com.airline.commonlib.payload.request;


import com.airline.commonlib.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AirlineRequest {

    @NotBlank(message = "IATA code is required")
    @Size(min = 2, max = 3, message = "IATA code must have 3 letters")
    private String iataCode;
    @NotBlank(message = "ICAO code is required")
    @Size(min = 3, max = 4, message = "ICAO code must have 4 letters")
    private String icaoCode;
    @NotBlank(message = "Airline name is required")
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
