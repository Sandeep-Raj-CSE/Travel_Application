package com.project.UBER.TravelApp.controllers;

import com.project.UBER.TravelApp.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping("/{userId}/add")
    public String addMoney(@PathVariable Long userId, @RequestParam Double amount){
        return walletService.addMoney(userId,amount);
    }


    @GetMapping("/{userID}/balance")
    public Double getBalance(@PathVariable Long userId){
        return walletService.getBalance(userId);
    }


}
