package com.project.UBER.TravelApp.repositories;

import com.project.UBER.TravelApp.model.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {
    List<RideRequest> findByStatus(String status);
}
