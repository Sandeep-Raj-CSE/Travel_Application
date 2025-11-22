package com.project.UBER.TravelApp.strategy.fare;

import com.project.UBER.TravelApp.model.Ride;

public interface FareCalculationStrategy {
    double calculateFare(Ride ride);
}
