package com.project.UBER.TravelApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Wallet {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    private Double balance = 100.00;
}
