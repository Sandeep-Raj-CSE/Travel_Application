package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.dto.AcceptRideDto;
import com.project.UBER.TravelApp.dto.RideRequestDto;
import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.model.RideRequest;
import com.project.UBER.TravelApp.services.RideRequestService;
import com.project.UBER.TravelApp.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride")
public class RideController {

    @Autowired
    RideRequestService rideRequestService;

    @Autowired
    RideService rideService;

    // 1. Create request
    @PostMapping("/request")
    public RideRequest createRequest(@RequestBody RideRequestDto dto) {
        RideRequest req = RideRequest.builder()
                .riderId(dto.getRiderId())
                .pickupLocation(dto.getPickupLocation())
                .dropLocation(dto.getDropLocation())
                .build();

        return rideRequestService.createRequest(req);
    }

    // 2. Match driver (system)
    @PostMapping("/request/{requestId}/match")
    public Driver matchDriver(@PathVariable Long requestId) {
        return rideRequestService.matchDriver(requestId);
    }

    // 3. Driver accepts matched request -> creates Ride
    @PostMapping("/request/{requestId}/accept")
    public Ride acceptRide(@PathVariable Long requestId, @RequestBody AcceptRideDto dto) {
        return rideService.acceptRide(requestId, dto.getDriverId());
    }

    // 4. Start ride
    @PutMapping("/{rideId}/start")
    public Ride startRide(@PathVariable Long rideId) {
        return rideService.startRide(rideId);
    }

    // 5. End ride
    @PutMapping("/{rideId}/end")
    public Ride endRide(@PathVariable Long rideId) {
        return rideService.endRide(rideId);
    }
}