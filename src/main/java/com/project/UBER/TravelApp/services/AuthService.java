package com.project.UBER.TravelApp.services;

import com.project.UBER.TravelApp.dto.LoginRequest;
import com.project.UBER.TravelApp.dto.SignupRequest;
import com.project.UBER.TravelApp.model.User;
import com.project.UBER.TravelApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public String signup(SignupRequest req){
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .role(req.getRole())
                .build();


        userRepository.save(user);
        return "User Signup Successfully";
    }


    public String login(LoginRequest req){
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Exception"));


        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Password");

        return "Login Successfully";
    }
}
