package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.enums.RideStatus;
import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.model.Rider;
import com.project.UBER.TravelApp.repositories.DriverRepository;
import com.project.UBER.TravelApp.repositories.RideRepository;
import com.project.UBER.TravelApp.repositories.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    RideRepository rideRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RiderRepository riderRepository;

    public String rateDriver(Long rideId, Integer rating){
        Ride ride = rideRepository.findById(rideId).
                orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus() != RideStatus.COMPLETED){
            throw new RuntimeException("Ride not completed it");
        }
        ride.setRiderRating(rating);
        rideRepository.save(ride);

        Driver driver = driverRepository.findById(ride.getDriverId()).orElseThrow(()->new RuntimeException("Driver not found"));

        double newRating = (driver.getRating() + rating)/2.0;

        driver.setRating(newRating);
        driverRepository.save(driver);

        return "Driver rated Successfully";
    }



    public String rateRider(Long rideId, Integer rating){
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(()-> new RuntimeException("Ride not found"));

        if(ride.getStatus() != RideStatus.COMPLETED){
            throw new RuntimeException("Ride not completed Yet");
        }

        ride.setDriverRating(rating);
        rideRepository.save(ride);

        Rider rider = riderRepository.findById(ride.getRiderId())
                .orElseThrow(()-> new RuntimeException("Rider Not found"));

        double newRating = (rider.getRating() + rating)/2.0;
        rider.setRating(newRating);
        riderRepository.save(rider);

        return "Rider rated Successfully";
    }
}
