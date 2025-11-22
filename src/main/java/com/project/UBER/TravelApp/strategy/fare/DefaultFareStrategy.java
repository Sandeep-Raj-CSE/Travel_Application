package com.project.UBER.TravelApp.strategy.fare;

import com.project.UBER.TravelApp.model.Ride;
import org.springframework.stereotype.Component;

@Component("defaultFareStrategy")
public class DefaultFareStrategy implements FareCalculationStrategy {

    private static final double BASE_FARE = 30.0;
    private static final double COST_PER_KM = 12.0;

    @Override
    public double calculateFare(Ride ride) {

        if (ride.getDistanceKm() == null) return BASE_FARE;

        return BASE_FARE + (ride.getDistanceKm() * COST_PER_KM);
    }
}
