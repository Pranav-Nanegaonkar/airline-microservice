package com.airline.core.controller;


import com.airline.commonlib.enums.AirlineStatus;
import com.airline.commonlib.payload.request.AirlineRequest;
import com.airline.commonlib.payload.request.AirlineUpdateRequest;
import com.airline.commonlib.payload.response.AirlineDropDownItem;
import com.airline.commonlib.payload.response.AirlineResponse;
import com.airline.commonlib.payload.response.ApiResponse;
import com.airline.core.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(
            @Valid @RequestBody AirlineRequest request,
            @RequestHeader("X-User-Id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airlineService.createAirline(request, id));
    }

    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwnerId(
            @RequestHeader("X-User-Id") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAirlineByOwnerId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAirlineById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.status(HttpStatus.OK).body(airlineService.getAllAirlines(pageable));
    }

    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(
            @Valid @RequestBody AirlineUpdateRequest request,
            @RequestHeader("X-User-Id") Long id
    ) {
        return ResponseEntity.ok(airlineService.updateAirline(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirline(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long ownerId
    ) {

        airlineService.deleteAirline(id, ownerId);
        return ResponseEntity.ok(new ApiResponse("Airline deleted successfully"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AirlineResponse> changeStatusByAdmin(
            @PathVariable Long id,
            @RequestBody AirlineStatus status
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(airlineService.changeStatusByAdmin(id, status));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropDownItem>> getAirlineDropDown() {
        return ResponseEntity.ok(airlineService.airlineDropDownItem());
    }
}





















