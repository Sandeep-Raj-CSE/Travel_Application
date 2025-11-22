package com.project.UBER.TravelApp.dto;


import com.project.UBER.TravelApp.enums.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionFilterDto {
    private Long userId;
    private TransactionType type;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
