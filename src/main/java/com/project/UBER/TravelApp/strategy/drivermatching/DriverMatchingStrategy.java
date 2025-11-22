package com.project.UBER.TravelApp.strategy.drivermatching;

import com.project.UBER.TravelApp.model.Driver;
import com.project.UBER.TravelApp.model.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    Driver findDriver(RideRequest request, List<Driver> candidates);
}
