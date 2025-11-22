package com.project.UBER.TravelApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BlacklistedToken {
    @Id
    @GeneratedValue
    private Long id;
    private String token;
}