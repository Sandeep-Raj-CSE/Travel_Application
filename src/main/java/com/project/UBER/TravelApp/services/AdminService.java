package com.project.UBER.TravelApp.services;

import com.project.UBER.TravelApp.dto.ApproveDriverDto;
import com.project.UBER.TravelApp.enums.AdminActionType;
import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.User;
import com.project.UBER.TravelApp.repositories.DriverRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import com.project.UBER.TravelApp.utils.AdminLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    AdminLogger adminLogger;

    public String approveDriver(ApproveDriverDto dto) {
        User user = userRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        if (!user.getRole().equals("DRIVER"))
            throw new RuntimeException("User is not a driver");

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver profile missing"));

        driver.setAvailable(true);
        driverRepository.save(driver);

        adminLogger.log(dto.getAdminId(), dto.getDriverId(),
                AdminActionType.APPROVE_DRIVER, "Driver approved");

        return "Driver Approved Successfully";
    }

    public String blockUser(Long adminId, Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        u.setRole("BLOCKED");
        userRepository.save(u);

        adminLogger.log(adminId, userId, AdminActionType.BLOCK_USER,
                "User has been blocked");

        return "User Blocked";
    }

    public String unblockUser(Long adminId, Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        u.setRole("RIDER");  // default
        userRepository.save(u);

        adminLogger.log(adminId, userId, AdminActionType.UNBLOCK_USER,
                "User has been unblocked");

        return "User Unblocked";
    }

    public String makeAdmin(Long adminId, Long userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        u.setRole("ADMIN");
        userRepository.save(u);

        adminLogger.log(adminId, userId, AdminActionType.MAKE_ADMIN,
                "User promoted to Admin");

        return "User Promoted to Admin";
    }

    public String systemHealthCheck(Long adminId) {
        adminLogger.log(adminId, 0L, AdminActionType.SYSTEM_CHECK,
                "System health check performed");

        return "System is Running Normally!";
    }
}
