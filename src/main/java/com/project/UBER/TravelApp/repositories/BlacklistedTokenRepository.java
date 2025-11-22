package com.project.UBER.TravelApp.repositories;

import com.project.UBER.TravelApp.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {}
