package com.airline.locationservice.entity;

import com.airline.commonlib.embeddable.Address;
import com.airline.commonlib.embeddable.GeoCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;

@Getter
@Setter
@Builder
@Entity
@Table(name = "airports")
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private ZoneId timeZoneId;

    @ManyToOne
    @JsonIgnore
    private City city;
}
