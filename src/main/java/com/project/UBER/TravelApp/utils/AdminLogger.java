package com.project.UBER.TravelApp.utils;

import com.project.UBER.TravelApp.enums.AdminActionType;
import com.project.UBER.TravelApp.model.AdminLog;
import com.project.UBER.TravelApp.model.AdminLog;
import com.project.UBER.TravelApp.repositories.AdminLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminLogger {

    @Autowired
    AdminLogRepository adminLogRepository;

    public void log(Long adminId, Long targetUser, AdminActionType type, String msg) {
        AdminLog log = AdminLog.builder()
                .adminId(adminId)
                .targetUserId(targetUser)
                .actionType(type)
                .description(msg)
                .build();

        adminLogRepository.save(log);
    }
}
