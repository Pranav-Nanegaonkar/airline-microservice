package com.airline.service.impl;

import com.airline.entity.City;
import com.airline.exception.ApiException;
import com.airline.mapper.CityMapper;
import com.airline.payload.request.CityRequest;
import com.airline.payload.request.CityUpdateRequest;
import com.airline.payload.response.BulkCityResponse;
import com.airline.payload.response.CityResponse;
import com.airline.repository.CityRepository;
import com.airline.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) {

        if (cityRepository.existsByCityCode(request.getCityCode())) {

            throw ApiException.badRequest("City with given city code already exist");
        }

        City city = CityMapper.toEntity(request);
        City result = cityRepository.save(city);

        return CityMapper.toResponse(result);
    }


    @Override
    public BulkCityResponse createCitiesBulk(List<CityRequest> requests) {

        List<CityResponse> created = new ArrayList<>();
        List<BulkCityResponse.FailedCity> failed = new ArrayList<>();

        // Guard against duplicate cityCodes WITHIN the same incoming batch
        Set<String> seenInBatch = new HashSet<>();

        for (CityRequest request : requests) {
            String code = request.getCityCode();

            if (!seenInBatch.add(code)) {
                failed.add(BulkCityResponse.FailedCity.builder()
                        .cityCode(code)
                        .reason("Duplicate city code within request payload")
                        .build());
                continue;
            }

            if (cityRepository.existsByCityCode(code)) {
                failed.add(BulkCityResponse.FailedCity.builder()
                        .cityCode(code)
                        .reason("City with given city code already exists in DB")
                        .build());
                continue;
            }

            try {
                City city = CityMapper.toEntity(request);
                City saved = cityRepository.save(city);
                created.add(CityMapper.toResponse(saved));
            } catch (Exception ex) {
                failed.add(BulkCityResponse.FailedCity.builder()
                        .cityCode(code)
                        .reason("Unexpected error: " + ex.getMessage())
                        .build());
            }
        }

        return BulkCityResponse.builder()
                .totalReceived(requests.size())
                .successCount(created.size())
                .failureCount(failed.size())
                .created(created)
                .failed(failed)
                .build();
    }

    @Override
    public CityResponse getCityById(Long id) {

        City city = cityRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("City not exist by id")
        );
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityUpdateRequest request) {
        City city = cityRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("City not exist by id")
        );
        City updatedCity = CityMapper.updateCity(city, request);
        City result = cityRepository.save(updatedCity);
        return CityMapper.toResponse(result);
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("City not exist by id")
        );
        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        Page<City> cities = cityRepository.findAll(pageable);
        return cities.map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        Page<City> cities = cityRepository.searchByKeyword(keyword, pageable);
        return cities.map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCitiesByCountryCode(String countryCode, Pageable pageable) {
        Page<City> cities = cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable);
        return cities.map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
