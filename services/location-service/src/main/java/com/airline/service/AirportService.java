package com.airline.service;


import com.airline.payload.request.AirportRequest;
import com.airline.payload.request.AirportUpdateRequest;
import com.airline.payload.request.CityRequest;
import com.airline.payload.response.AirportResponse;
import com.airline.payload.response.BulkAirportResponse;
import com.airline.payload.response.BulkCityResponse;

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
