package com.project.UBER.TravelApp.controllers;


import com.project.UBER.TravelApp.model.Rider;
import com.project.UBER.TravelApp.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
public class RiderController {

    @Autowired
    RiderService riderService;

    @PostMapping("/register/{userId}")
    public String register(@PathVariable Long userId){
        return riderService.registerRider(userId);
    }

    @GetMapping("/{userId}")
    public Rider getRider(@PathVariable Long userId){
        return riderService.getRider(userId);
    }
}
