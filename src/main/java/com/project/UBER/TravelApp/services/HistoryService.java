package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.dto.RideFilterDto;
import com.project.UBER.TravelApp.dto.TransactionFilterDto;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.model.Transaction;
import com.project.UBER.TravelApp.repositories.RideRepository;
import com.project.UBER.TravelApp.repositories.TransactionRepository;
import com.project.UBER.TravelApp.utils.DateRangeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    RideRepository rideRepository;

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Basic ride history by rider
     */
    public List<Ride> getRidesForRider(RideFilterDto filter) {
        // simple implementation: use repository methods or a custom query if needed
        LocalDateTime from = DateRangeUtil.startofDay(filter.getFromDate());
        LocalDateTime to = DateRangeUtil.endofDay(filter.getToDate());

        if (filter.getRiderId() != null) {
            // naive approach: repository.findAll() filtering in-memory — replace with custom query for production
            return rideRepository.findAll().stream()
                    .filter(r -> r.getRiderId().equals(filter.getRiderId()))
                    .filter(r -> (filter.getStatus() == null || r.getStatus() == filter.getStatus()))
                    .filter(r -> (r.getStratTime() == null ? true : (r.getStratTime().isAfter(from) && r.getStratTime().isBefore(to))))
                    .toList();
        }
        // fallback: all rides (limit in production)
        return rideRepository.findAll();
    }

    public List<Ride> getRidesForDriver(RideFilterDto filter) {
        LocalDateTime from = DateRangeUtil.startofDay(filter.getFromDate());
        LocalDateTime to = DateRangeUtil.endofDay(filter.getToDate());

        if (filter.getDriverId() != null) {
            return rideRepository.findAll().stream()
                    .filter(r -> r.getDriverId().equals(filter.getDriverId()))
                    .filter(r -> (filter.getStatus() == null || r.getStatus() == filter.getStatus()))
                    .filter(r -> (r.getStratTime() == null ? true : (r.getStratTime().isAfter(from) && r.getStratTime().isBefore(to))))
                    .toList();
        }
        return rideRepository.findAll();
    }

    public List<Transaction> getTransactions(TransactionFilterDto filter) {
        LocalDateTime from = DateRangeUtil.startofDay(LocalDate.from(filter.getFromDate()));
        LocalDateTime to = DateRangeUtil.endofDay(LocalDate.from(filter.getToDate()));

        if (filter.getUserId() != null) {
            if (filter.getType() != null) {
                return transactionRepository.findByUserIdAndTypeOrderByCreatedAtDesc(filter.getUserId(), filter.getType());
            } else {
                return transactionRepository.findByUserIdAndDateRange(filter.getUserId(), from, to);
            }
        } else {
            return transactionRepository.findByTypeAndDateRange(filter.getType(), from, to);
        }
    }
}
