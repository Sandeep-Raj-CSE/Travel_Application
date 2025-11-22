package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.User;
import com.project.UBER.TravelApp.repositories.DriverRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    public String registerDriver(Long userId, String license, String vehicleNo){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not found"));

        if(!user.getRole().equals("DRIVER")){
            throw  new RuntimeException("User is not Driver Role");
        }

        Driver driver = Driver.builder()
                .user(user)
                .LicenceNumber(license)
                .VechileNumber(vehicleNo)
                .available(true)
                .rating(5.0)
                .build();

        driverRepository.save(driver);

        return "Driver Registered SuccessFully";
    }

    public String updateLocation(Long userId, String location){
        Driver driver = driverRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Driver Not found"));

        driver.setCurrentLocation(location);
        driverRepository.save(driver);
        return "Driver Location Update";
    }


    public String updateAvailability(Long userId, boolean status){
        Driver driver = driverRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Driver Not Found"));

        driver.setAvailable(status);
        driverRepository.save(driver);
        return "Driver Availability Updated";
    }
}
