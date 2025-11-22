package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.dto.RideFilterDto;
import com.project.UBER.TravelApp.dto.TransactionFilterDto;
import com.project.UBER.TravelApp.model.Ride;
import com.project.UBER.TravelApp.model.Transaction;
import com.project.UBER.TravelApp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @PostMapping("/rider")
    public List<Ride> riderHistory(@RequestBody RideFilterDto filter) {
        return historyService.getRidesForRider(filter);
    }

    @PostMapping("/driver")
    public List<Ride> driverHistory(@RequestBody RideFilterDto filter) {
        return historyService.getRidesForDriver(filter);
    }

    @PostMapping("/transactions")
    public List<Transaction> transactions(@RequestBody TransactionFilterDto filter) {
        return historyService.getTransactions(filter);
    }
}