package com.project.UBER.TravelApp.model;


import com.project.UBER.TravelApp.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long riderId;
    private Long driverId;

    private String pickupLocation;
    private String dropLocation;

    private Double distanceKm;
    private Double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;


    private LocalDateTime stratTime;
    private LocalDateTime endTime;


    private Integer riderRating;
    private Integer driverRating;




}
