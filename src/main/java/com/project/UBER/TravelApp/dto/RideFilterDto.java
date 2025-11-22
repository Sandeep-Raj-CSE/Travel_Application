package com.project.UBER.TravelApp.dto;


import com.project.UBER.TravelApp.enums.RideStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RideFilterDto {

    private Long riderId;
    private Long driverId;
    private RideStatus status;
    private LocalDate fromDate;
    private LocalDate toDate;

}
