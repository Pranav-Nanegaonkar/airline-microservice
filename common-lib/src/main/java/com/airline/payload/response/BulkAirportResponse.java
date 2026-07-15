package com.airline.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkAirportResponse {

    private int totalReceived;
    private int successCount;
    private int failureCount;
    private List<AirportResponse> created;
    private List<FailedAirport> failed;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FailedAirport {
        private String iataCode;
        private String reason;
    }
}
