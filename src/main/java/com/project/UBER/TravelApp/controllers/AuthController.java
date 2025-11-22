package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.configs.JwtUtils;
import com.project.UBER.TravelApp.dto.LoginRequest;
import com.project.UBER.TravelApp.dto.JwtResponse;
import com.project.UBER.TravelApp.dto.RefreshTokenRequest;
import com.project.UBER.TravelApp.model.BlacklistedToken;
import com.project.UBER.TravelApp.repositories.BlacklistedTokenRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlacklistedTokenRepository blacklistRepo;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        var user = userRepository.findByEmail(req.getEmail()).get();

        String accessToken = jwtUtils.generateAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtUtils.generateRefreshToken(user.getEmail());

        return new JwtResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody RefreshTokenRequest req) {
        String username = jwtUtils.extractUsername(req.getRefreshToken());

        String newAccessToken = jwtUtils.generateAccessToken(username, "RIDER");
        return new JwtResponse(newAccessToken, req.getRefreshToken());
    }

    // 🔥 This is the logout API you asked for
    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String header) {
        String token = header.substring(7);
        blacklistRepo.save(new BlacklistedToken());
        return "Logged out successfully";
    }
}
