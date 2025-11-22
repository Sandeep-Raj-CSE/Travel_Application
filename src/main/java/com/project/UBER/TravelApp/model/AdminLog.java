package com.project.UBER.TravelApp.model;

import com.project.UBER.TravelApp.enums.AdminActionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adminId;
    private Long targetUserId;

    @Enumerated(EnumType.STRING)
    private AdminActionType actionType;

    private String description;

    private LocalDateTime timestamp = LocalDateTime.now();
}
