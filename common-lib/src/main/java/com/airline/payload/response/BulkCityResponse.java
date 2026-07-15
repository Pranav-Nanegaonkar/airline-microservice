package com.airline.payload.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulkCityResponse {

    private int totalReceived;
    private int successCount;
    private int failureCount;
    private List<CityResponse> created;
    private List<FailedCity> failed;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FailedCity {
        private String cityCode;
        private String reason;
    }
}
