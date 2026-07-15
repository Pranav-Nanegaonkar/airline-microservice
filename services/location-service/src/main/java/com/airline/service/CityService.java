package com.airline.service;

import com.airline.payload.request.CityRequest;
import com.airline.payload.request.CityUpdateRequest;
import com.airline.payload.response.BulkCityResponse;
import com.airline.payload.response.CityResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    CityResponse createCity(CityRequest request) ;

    CityResponse getCityById(Long id) ;

    CityResponse updateCity(Long id, CityUpdateRequest request);

    void deleteCity(Long id) ;

    Page<CityResponse> getAllCities(Pageable pageable);

    Page<CityResponse> searchCities(String keyword, Pageable pageable);

    Page<CityResponse> searchCitiesByCountryCode(String countryCode, Pageable pageable);

    boolean cityExists(String cityCode);

    BulkCityResponse createCitiesBulk(List<CityRequest> cities);
}
