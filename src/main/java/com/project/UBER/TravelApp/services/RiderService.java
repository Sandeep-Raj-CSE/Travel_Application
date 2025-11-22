package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.model.Rider;
import com.project.UBER.TravelApp.model.User;
import com.project.UBER.TravelApp.repositories.RiderRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RiderRepository riderRepository;

    public String registerRider(Long userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if(!user.getRole().equals("RIDER")){
            throw  new RuntimeException("User is Rider Role");
        }


        Rider rider = Rider.builder()
                .user(user)
                .rating(5.0)
                .build();

        riderRepository.save(rider);

        return "Rider Profile Created";
    }

    public Rider getRider(Long userId){
        return riderRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Rider not found"));
    }

}
