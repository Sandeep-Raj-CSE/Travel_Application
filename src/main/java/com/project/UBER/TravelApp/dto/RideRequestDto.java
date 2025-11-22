package com.project.UBER.TravelApp.dto;


import lombok.Data;

@Data
public class RideRequestDto {
    private Long riderId;
    private String pickupLocation;
    private String dropLocation;
}
