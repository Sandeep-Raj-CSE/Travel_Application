package com.project.UBER.TravelApp.controllers;


import com.project.UBER.TravelApp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")

public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/register/{userId}")
    public String register(@PathVariable Long userId, @RequestParam String licence, @RequestParam String vehicle){

        return driverService.registerDriver(userId,licence,vehicle);
    }

    @PutMapping("/{userId}/availability")
    public String updateAvailablity(@PathVariable Long userId, @RequestParam Boolean status){
        return driverService.updateAvailability(userId,status);
    }

}
