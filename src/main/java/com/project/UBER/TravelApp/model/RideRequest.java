package com.project.UBER.TravelApp.model;

import com.project.UBER.TravelApp.enums.RideRequestStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
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
public class RideRequest {

    @Id
    private long id;

    private Long riderId;
    private String pickupLocation;
    private String dropLocation;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus status = RideRequestStatus.PENDING;
    private LocalDateTime requestedAt = LocalDateTime.now();

    private Long assignedDriverId; // runnable

}

