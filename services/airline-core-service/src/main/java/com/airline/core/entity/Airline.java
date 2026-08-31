package com.airline.core.entity;


import com.airline.commonlib.embeddable.Support;
import com.airline.commonlib.enums.AirlineStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String iataCode;

    @Column(nullable = false, unique = true)
    private String icaoCode;

    @Column(nullable = false)
    private String name;
    private String alias;

    @Column(nullable = false)
    private Long ownerId;


    private String logoUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    private AirlineStatus status = AirlineStatus.ACTIVE;

    private String alliance;

    private Long headquartersCityId;
    private Long updatedById;

    @Embedded
    private Support support;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
