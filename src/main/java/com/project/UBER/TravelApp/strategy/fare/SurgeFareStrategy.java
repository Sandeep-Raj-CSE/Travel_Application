package com.project.UBER.TravelApp.strategy.fare;


import com.project.UBER.TravelApp.model.Ride;
import org.springframework.stereotype.Component;

@Component("SurgeFareStrategy")
public class SurgeFareStrategy implements FareCalculationStrategy {

    private static final double MULTI_PLIER = 2.0;
    private static final double BASE_FAIR = 30.0;
    private static final double PER_KM = 10.0;

    @Override
    public double calculateFare(Ride ride){
        double distance = ride.getDistanceKm() == null ? 0.0 : ride.getDistanceKm();
        return Math.max(BASE_FAIR,(BASE_FAIR + distance * PER_KM)* MULTI_PLIER);
    }

}
