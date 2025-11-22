package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.dto.ApproveDriverDto;
import com.project.UBER.TravelApp.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/approve-driver")
    public String approveDriver(@RequestBody ApproveDriverDto dto) {
        return adminService.approveDriver(dto);
    }

    @PostMapping("/block/{adminId}/{userId}")
    public String blockUser(@PathVariable Long adminId,
                            @PathVariable Long userId) {
        return adminService.blockUser(adminId, userId);
    }

    @PostMapping("/unblock/{adminId}/{userId}")
    public String unblockUser(@PathVariable Long adminId,
                              @PathVariable Long userId) {
        return adminService.unblockUser(adminId, userId);
    }

    @PostMapping("/make-admin/{adminId}/{userId}")
    public String promoteToAdmin(@PathVariable Long adminId,
                                 @PathVariable Long userId) {
        return adminService.makeAdmin(adminId, userId);
    }

    @GetMapping("/system-health/{adminId}")
    public String systemHealth(@PathVariable Long adminId) {
        return adminService.systemHealthCheck(adminId);
    }
}
