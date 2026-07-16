package com.airline.locationservice.controller;

import com.airline.commonlib.payload.request.BulkCityRequest;
import com.airline.commonlib.payload.request.CityRequest;
import com.airline.commonlib.payload.request.CityUpdateRequest;
import com.airline.commonlib.payload.response.ApiResponse;
import com.airline.commonlib.payload.response.BulkCityResponse;
import com.airline.commonlib.payload.response.CityResponse;
import com.airline.locationservice.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest request) throws Exception {
        CityResponse city = cityService.createCity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @PostMapping("/bulk")
    public ResponseEntity<BulkCityResponse> createCitiesBulk(@Valid @RequestBody BulkCityRequest request) {
        BulkCityResponse response = cityService.createCitiesBulk(request.getCities());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) throws Exception {
        CityResponse city = cityService.getCityById(id);
        return ResponseEntity.ok(city);
    }

    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(cityService.getAllCities(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable Long id,
            @Valid @RequestBody CityUpdateRequest request
    ) throws Exception {
        CityResponse city = cityService.updateCity(id, request);
        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(
            @PathVariable Long id
    ) throws Exception {
        cityService.deleteCity(id);

        return ResponseEntity.ok(new ApiResponse("City has been deleted"));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(cityService.searchCities(keyword, pageable));
    }
}

