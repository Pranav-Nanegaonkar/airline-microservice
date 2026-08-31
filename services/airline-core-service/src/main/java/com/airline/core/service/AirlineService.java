package com.airline.core.service;

import com.airline.commonlib.enums.AirlineStatus;
import com.airline.commonlib.payload.request.AirlineRequest;
import com.airline.commonlib.payload.request.AirlineUpdateRequest;
import com.airline.commonlib.payload.response.AirlineDropDownItem;
import com.airline.commonlib.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {
    AirlineResponse createAirline(AirlineRequest request, Long ownerId);

    AirlineResponse getAirlineByOwnerId(Long ownerId);

    AirlineResponse getAirlineById(Long airlineId);

    Page<AirlineResponse> getAllAirlines(Pageable pageable);

    AirlineResponse updateAirline(AirlineUpdateRequest request, Long ownerId);

    void deleteAirline(Long airlineId, Long ownerId);

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status);

    List<AirlineDropDownItem> airlineDropDownItem();
}
