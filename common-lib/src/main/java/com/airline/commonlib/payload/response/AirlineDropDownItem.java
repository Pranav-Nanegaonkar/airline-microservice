package com.airline.commonlib.payload.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDropDownItem {

    private Long id;
    private String iataCode;
    private String icaoCode;
    private String name;
    private String country;
}
