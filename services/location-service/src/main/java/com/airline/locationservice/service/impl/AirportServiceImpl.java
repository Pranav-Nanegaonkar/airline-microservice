package com.airline.locationservice.service.impl;

import com.airline.locationservice.entity.Airport;
import com.airline.locationservice.entity.City;
import com.airline.locationservice.exception.ApiException;
import com.airline.locationservice.mapper.AirportMapper;
import com.airline.commonlib.payload.request.AirportRequest;
import com.airline.commonlib.payload.request.AirportUpdateRequest;
import com.airline.commonlib.payload.response.AirportResponse;
import com.airline.commonlib.payload.response.BulkAirportResponse;
import com.airline.locationservice.repository.AirportRepository;
import com.airline.locationservice.repository.CityRepository;
import com.airline.locationservice.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) {

        if (airportRepository.existsByIataCode(request.getIataCode())) {
            throw ApiException.badRequest("The Airport with IATA code is already exists");
        }

        City city = cityRepository.findById(request.getCityId()).orElseThrow(() ->
                ApiException.badRequest("The city with id does not exist"));

        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport result = airportRepository.save(airport);

        return AirportMapper.toResponse(result);
    }

    @Override
    public BulkAirportResponse createAirportsBulk(List<AirportRequest> requests) {

        List<AirportResponse> created = new ArrayList<>();
        List<BulkAirportResponse.FailedAirport> failed = new ArrayList<>();

        // Guard against duplicate iataCodes WITHIN the same incoming batch
        Set<String> seenInBatch = new HashSet<>();


        for (AirportRequest request : requests) {
            String code = request.getIataCode();

            if (!seenInBatch.add(code)) {
                failed.add(BulkAirportResponse.FailedAirport.builder()
                        .iataCode(code)
                        .reason("Duplicate iata code within request payload")
                        .build());
                continue;
            }

            if (airportRepository.existsByIataCode(code)) {
                failed.add(BulkAirportResponse.FailedAirport.builder()
                        .iataCode(code)
                        .reason("Airport with given iata code already exists in DB")
                        .build());
                continue;
            }

            try {
                City city = cityRepository.findById(request.getCityId()).orElseThrow(() ->
                        ApiException.badRequest("The city with id does not exist"));

                Airport airport = AirportMapper.toEntity(request);
                airport.setCity(city);
                Airport saved = airportRepository.save(airport);
                created.add(AirportMapper.toResponse(saved));
            } catch (Exception ex) {
                failed.add(BulkAirportResponse.FailedAirport.builder()
                        .iataCode(code)
                        .reason("Unexpected error: " + ex.getMessage())
                        .build());
            }
        }

        return BulkAirportResponse.builder()
                .totalReceived(requests.size())
                .successCount(created.size())
                .failureCount(failed.size())
                .created(created)
                .failed(failed)
                .build();
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(() ->
                ApiException.notFound("The airport with id does not exist"));

        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .toList();
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportUpdateRequest request) {

        Airport airport = airportRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("The airport with id does not exist"));

        if (request.getIataCode() != null
                && !request.getIataCode().equals(airport.getIataCode())
                && airportRepository.existsByIataCode(request.getIataCode())) {
            throw ApiException.badRequest("The Airport with IATA code is already exists");
        }
        Airport updateAirport = AirportMapper.updateAirport(airport, request);
        Airport save = airportRepository.save(updateAirport);
        return AirportMapper.toResponse(save);
    }

    @Override
    public void deleteAirport(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(() ->
                ApiException.badRequest("The airport with id does not exist"));

        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {

        return airportRepository.findAllByCityId(cityId)
                .stream()
                .map(AirportMapper::toResponse)
                .toList();
    }
}
