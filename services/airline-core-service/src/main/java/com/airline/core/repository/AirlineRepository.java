package com.airline.core.repository;

import com.airline.commonlib.enums.AirlineStatus;
import com.airline.core.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Optional<Airline> findByOwnerId(Long ownerId);

    List<Airline> findAllByStatus(AirlineStatus status);
}
