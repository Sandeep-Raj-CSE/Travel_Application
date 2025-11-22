package com.project.UBER.TravelApp.repositories;

import com.project.UBER.TravelApp.model.AdminLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {
}
