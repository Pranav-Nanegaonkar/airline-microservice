package com.airline.repository;

import com.airline.entity.Airport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    boolean existsByIataCode(String iataCode);

    List<Airport> findAllByCityId(Long cityId);
}
