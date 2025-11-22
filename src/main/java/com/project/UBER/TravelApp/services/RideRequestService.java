package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.enums.RideRequestStatus;
import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.RideRequest;
import com.project.UBER.TravelApp.model.User;
import com.project.UBER.TravelApp.repositories.DriverRepository;
import com.project.UBER.TravelApp.repositories.RideRequestRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import com.project.UBER.TravelApp.services.notifications.EmailNotificationService;
import com.project.UBER.TravelApp.strategy.drivermatching.DriverMatchingStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestService {

    @Autowired
    RideRequestRepository rideRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    EmailNotificationService emailNotificationService;

    @Autowired
    @Qualifier("nearestDriverStrategy")
    private DriverMatchingStrategy driverMatchingStrategy;

    @Transactional
    public RideRequest createRequest(RideRequest request){
        User user = userRepository.findById(request.getRiderId()).orElseThrow(()-> new RuntimeException("Rider Not Found"));

        if(!"RIDER" .equals(user.getRole())) throw  new RuntimeException("User is not Rider");
        request.setStatus(RideRequestStatus.PENDING);
        return rideRequestRepository.save(request);
    }


    @Transactional
    public Driver matchDriver(Long requestId){

        RideRequest req = rideRequestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request Not found"));

        if(req.getStatus() != RideRequestStatus.PENDING){
            throw new RuntimeException("Request not pending");
        }

        List<Driver> availableDrivers = driverRepository.findAll().stream()
                .filter(Driver::isAvailable)
                .toList();


        Driver matched = driverMatchingStrategy.findDriver(req,availableDrivers);
        if(matched == null){
            req.setStatus(RideRequestStatus.CANCELLED);
            rideRequestRepository.save(req);
            return null;
        }


        req.setAssignedDriverId(matched.getId());
        req.setStatus(RideRequestStatus.MATCHED);
        rideRequestRepository.save(req);

        return matched;





    }
}
