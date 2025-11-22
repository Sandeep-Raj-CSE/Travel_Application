package com.project.UBER.TravelApp.controllers;


import com.project.UBER.TravelApp.dto.RateDriverDto;
import com.project.UBER.TravelApp.dto.RateRiderDto;
import com.project.UBER.TravelApp.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping("/driver")
    public String rateDriver(@RequestBody RateDriverDto dto){
        return ratingService.rateDriver(dto.getRideId(),dto.getRating());
    }

    @PostMapping("/rider")
    public String rateRider(@RequestBody RateRiderDto dto){
        return ratingService.rateRider(dto.getRideId(), dto.getRating());
    }

}
