package com.project.UBER.TravelApp.strategy.drivermatching;

import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.RideRequest;
import com.project.UBER.TravelApp.utils.DistanceCalculator;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component("nearestDriverStrategy")
public class NearestDriverStrategy implements DriverMatchingStrategy {

    @Override
    public Driver findDriver(RideRequest request, List<Driver> candidates) {

        if (candidates == null || candidates.isEmpty()) return null;

        return candidates.stream()
                .min(Comparator.comparingDouble(
                        d -> DistanceCalculator.estimateDistance(
                                request.getPickupLocation(),
                                d.getCurrentLocation()
                        )
                ))
                .orElse(null);
    }
}
