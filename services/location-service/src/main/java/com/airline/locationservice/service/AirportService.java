package com.airline.locationservice.service;


import com.airline.commonlib.payload.request.AirportRequest;
import com.airline.commonlib.payload.request.AirportUpdateRequest;
import com.airline.commonlib.payload.response.AirportResponse;
import com.airline.commonlib.payload.response.BulkAirportResponse;

import java.util.List;

public interface AirportService {

    AirportResponse createAirport(AirportRequest request);

    AirportResponse getAirportById(Long id);

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id, AirportUpdateRequest request);

    void deleteAirport(Long id);

    List<AirportResponse> getAirportsByCityId(Long cityId);

    BulkAirportResponse createAirportsBulk(List<AirportRequest> airports);
}
