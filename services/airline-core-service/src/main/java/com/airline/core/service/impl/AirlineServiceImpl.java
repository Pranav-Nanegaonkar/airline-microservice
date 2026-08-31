package com.airline.core.service.impl;

import com.airline.commonlib.enums.AirlineStatus;
import com.airline.commonlib.exception.ApiException;
import com.airline.commonlib.payload.request.AirlineRequest;
import com.airline.commonlib.payload.request.AirlineUpdateRequest;
import com.airline.commonlib.payload.response.AirlineDropDownItem;
import com.airline.commonlib.payload.response.AirlineResponse;
import com.airline.core.entity.Airline;
import com.airline.core.mapper.AirlineMapper;
import com.airline.core.repository.AirlineRepository;
import com.airline.core.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        Airline airline = AirlineMapper.toEntity(request, ownerId);
        Airline save = airlineRepository.save(airline);
        return AirlineMapper.toResponse(save);
    }

    @Override
    public AirlineResponse getAirlineByOwnerId(Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(() ->
                ApiException.badRequest("Airline does not exist with owner id")
        );

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long airlineId) {

        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() ->
                ApiException.badRequest("Airline does not exist with id"));

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {

        return airlineRepository.findAll(pageable).map(AirlineMapper::toResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineUpdateRequest request, Long ownerId) {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(() ->
                ApiException.badRequest("Airline does not exist with owner id"));
        Airline updated = AirlineMapper.updateAirline(airline, request);
        Airline save = airlineRepository.save(updated);
        return AirlineMapper.toResponse(save);
    }

    @Override
    public void deleteAirline(Long airlineId, Long ownerId) {
        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(() ->
                ApiException.badRequest("Airline does not exist with owner id")
        );
        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(() ->
                ApiException.badRequest("Airline does not exist with id"));

        airline.setStatus(status);
        Airline updated = airlineRepository.save(airline);
        return AirlineMapper.toResponse(updated);
    }

    @Override
    public List<AirlineDropDownItem> airlineDropDownItem() {
        return airlineRepository.findAllByStatus(AirlineStatus.ACTIVE).stream().map(a -> AirlineDropDownItem.builder()
                .id(a.getId())
                .iataCode(a.getIataCode())
                .icaoCode(a.getIcaoCode())
                .name(a.getName())
                .build()).toList();
    }
}
