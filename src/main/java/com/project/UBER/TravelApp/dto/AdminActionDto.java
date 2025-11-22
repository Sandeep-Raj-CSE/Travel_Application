package com.project.UBER.TravelApp.dto;

import com.project.UBER.TravelApp.enums.AdminActionType;
import lombok.Data;

@Data
public class AdminActionDto {
    private Long adminId;
    private Long targetUserId;
    private AdminActionType actionType;
    private String message;
}
