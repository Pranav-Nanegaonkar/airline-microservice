package com.airline.service.impl;

import com.airline.entity.Airport;
import com.airline.entity.City;
import com.airline.exception.ApiException;
import com.airline.mapper.AirportMapper;
import com.airline.payload.request.AirportRequest;
import com.airline.payload.request.AirportUpdateRequest;
import com.airline.payload.response.AirportResponse;
import com.airline.repository.AirportRepository;
import com.airline.repository.CityRepository;
import com.airline.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Airport updateAirport = AirportMapper.updateAirport(airport, request);
        Airport save = airportRepository.save(updateAirport);
        return AirportMapper.toResponse(save);
    }

    @Override
    public void deleteAirport(Long id) {

        if (airportRepository.existsById(id)) {
            throw ApiException.badRequest("The airport with id does not exist");
        }
        airportRepository.deleteById(id);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {

        return airportRepository.findAllByCityId(cityId)
                .stream()
                .map(AirportMapper::toResponse)
                .toList();
    }
}
