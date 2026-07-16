package com.airline.locationservice.repository;

import com.airline.locationservice.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    boolean existsByIataCode(String iataCode);
    
    List<Airport> findAllByCityId(Long cityId);
}
