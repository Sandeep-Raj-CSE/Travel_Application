package com.project.UBER.TravelApp.strategy.drivermatching;

import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.RideRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("highestRatedDriverStrategy")
public class HighestRatedDriverStrategy implements DriverMatchingStrategy {

    @Override
    public Driver findDriver(RideRequest request, List<Driver> candidates) {
        if (candidates == null || candidates.isEmpty()) return null;
        return candidates.stream()
                .max(Comparator.comparingDouble(Driver::getRating))
                .orElse(null);
    }
}
