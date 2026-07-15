package com.airline.controller;

import com.airline.payload.request.AirportRequest;
import com.airline.payload.request.AirportUpdateRequest;
import com.airline.payload.response.AirportResponse;
import com.airline.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(
            @Valid @RequestBody AirportRequest request
    ) {
        AirportResponse airport = airportService.createAirport(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(airport);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        List<AirportResponse> airports = airportService.getAllAirports();
        return ResponseEntity.status(HttpStatus.OK).body(airports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) {
        AirportResponse airport = airportService.getAirportById(id);
        return ResponseEntity.status(HttpStatus.OK).body(airport);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirPortByCityID(@PathVariable Long cityId) {
        List<AirportResponse> airports = airportService.getAirportsByCityId(cityId);
        return ResponseEntity.status(HttpStatus.OK).body(airports);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirportById(
            @PathVariable Long id,
            @Valid @RequestBody AirportUpdateRequest request
    ) {
        AirportResponse airport = airportService.updateAirport(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(airport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirportById(
            @PathVariable Long id
    ) {
        airportService.deleteAirport(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted airport successfully");
    }


}
