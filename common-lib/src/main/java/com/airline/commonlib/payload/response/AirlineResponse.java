package com.airline.commonlib.payload.response;

import com.airline.commonlib.dto.UserDTO;
import com.airline.commonlib.embeddable.Support;
import com.airline.commonlib.enums.AirlineStatus;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineResponse {
    private Long id;

    private String iataCode;
    private String icaoCode;

    private String name;
    private String alias;

    private String logoUrl;
    private String website;

    private AirlineStatus status;
    private String alliance;

    private Long headquartersCityId;
    private Long ownerId;
    private Long updatedById;

    private Support support;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
