package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.enums.TransactionType;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.repositories.RideRepository;
import com.project.UBER.TravelApp.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    RideRepository rideRepository;

    /**
     * Total revenue in range (sum of ride payments)
     */
    public Double totalRevenue(LocalDateTime from, LocalDateTime to) {
        Double sum = transactionRepository.sumAmountByTypeAndDateRange(TransactionType.RIDE_PAYMENT, from, to);
        return sum == null ? 0.0 : sum;
    }

    /**
     * Rides count per day in date-range (simple)
     */
    public Map<String, Long> ridesCountPerDay(LocalDateTime from, LocalDateTime to) {
        List<Ride> rides = rideRepository.findAll().stream()
                .filter(r -> r.getStratTime() != null && !r.getStratTime().isBefore(from) && !r.getStratTime().isAfter(to))
                .toList();

        return rides.stream().collect(Collectors.groupingBy(
                r -> r.getStratTime().toLocalDate().toString(),
                Collectors.counting()
        ));
    }

    /**
     * Top n drivers by completed rides (simple)
     */
    public List<Map.Entry<Long, Long>> topDrivers(int n, LocalDateTime from, LocalDateTime to) {
        List<Ride> rides = rideRepository.findAll().stream()
                .filter(r -> r.getStatus() != null && r.getStatus().name().equals("COMPLETED"))
                .filter(r -> r.getStratTime() != null && !r.getStratTime().isBefore(from) && !r.getStratTime().isAfter(to))
                .toList();

        Map<Long, Long> counts = new HashMap<>();
        for (Ride r : rides) {
            counts.put(r.getDriverId(), counts.getOrDefault(r.getDriverId(), 0L) + 1);
        }

        return counts.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .toList();
    }


}
