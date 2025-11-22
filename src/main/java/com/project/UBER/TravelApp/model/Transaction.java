package com.project.UBER.TravelApp.model;


import com.project.UBER.TravelApp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String Description;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Long relatedRideId;



}
