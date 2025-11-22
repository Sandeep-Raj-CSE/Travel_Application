package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.enums.RideStatus;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.model.RideRequest;
import com.project.UBER.TravelApp.repositories.DriverRepository;
import com.project.UBER.TravelApp.repositories.RideRepository;
import com.project.UBER.TravelApp.repositories.RideRequestRepository;
import com.project.UBER.TravelApp.services.notifications.EmailNotificationService;
import com.project.UBER.TravelApp.strategy.fare.FareCalculationStrategy;
import com.project.UBER.TravelApp.utils.DistanceCalculator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RideService {

    @Autowired
    RideRequestRepository rideRequestRepository;

    @Autowired
    RideRepository rideRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    WalletService walletService;

    @Autowired
    EmailNotificationService emailNotificationService;

    // choose fare strategy dynamically; default to defaultFareStrategy
    @Autowired
    @Qualifier("defaultFareStrategy")
    FareCalculationStrategy fareCalculationStrategy;

    @Transactional
    public Ride acceptRide(Long requestId, Long driverId) {
        RideRequest req = rideRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));

        if (req.getStatus() != com.project.UBER.TravelApp.enums.RideRequestStatus.MATCHED) {
            throw new RuntimeException("Request not matched");
        }

        if (!driverId.equals(req.getAssignedDriverId())) {
            throw new RuntimeException("Driver not assigned");
        }

        // create Ride
        Ride ride = Ride.builder()
                .riderId(req.getRiderId())
                .driverId(driverId)
                .pickupLocation(req.getPickupLocation())
                .dropLocation(req.getDropLocation())
                .status(RideStatus.ACCEPTED)
                .stratTime(null)
                .endTime(null)
                .build();

        // mark driver unavailable
        var driver = driverRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setAvailable(false);
        driverRepository.save(driver);

        req.setStatus(com.project.UBER.TravelApp.enums.RideRequestStatus.MATCHED);
        rideRequestRepository.save(req);

        return rideRepository.save(ride);
    }

    @Transactional
    public Ride startRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (ride.getStatus() != RideStatus.ACCEPTED) throw new RuntimeException("Ride not in accepted state");

        ride.setStratTime(LocalDateTime.now());
        ride.setStatus(RideStatus.ONGOING);
        // estimate distance
        double dist = DistanceCalculator.estimateDistance(ride.getPickupLocation(), ride.getDropLocation());
        ride.setDistanceKm(dist);

        return rideRepository.save(ride);
    }

    @Transactional
    public Ride endRide(Long rideId) {
        Ride ride = rideRepository.findById(rideId).orElseThrow(() -> new RuntimeException("Ride not found"));
        if (ride.getStatus() != RideStatus.ONGOING) throw new RuntimeException("Ride not ongoing");

        ride.setEndTime(LocalDateTime.now());
        ride.setStatus(RideStatus.COMPLETED);

        // calculate fare
        double fare = fareCalculationStrategy.calculateFare(ride);
        ride.setFare(fare);

        // deduct from rider wallet
        walletService.deductAmount(ride.getRiderId(), fare);

        // pay driver (simple add money)
        walletService.addtoUserWallet(ride.getDriverId(), fare);

        // mark driver available again
        var driver = driverRepository.findById(ride.getDriverId()).orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setAvailable(true);
        driverRepository.save(driver);

        return rideRepository.save(ride);

    }

}
