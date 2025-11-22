package com.project.UBER.TravelApp.services;


import com.project.UBER.TravelApp.enums.TransactionType;
import com.project.UBER.TravelApp.model.Transaction;
import com.project.UBER.TravelApp.model.Wallet;
import com.project.UBER.TravelApp.repositories.TransactionRepository;
import com.project.UBER.TravelApp.repositories.UserRepository;
import com.project.UBER.TravelApp.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;



    public String addMoney(Long userId, Double amount){
        if(amount < 10)
            throw new RuntimeException("Minimum amount is 10");

        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("wallet not found"));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        return "Money Added Successfully";
    }


    public Double getBalance(Long userId){
        Wallet wallet = walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet Not Found"));

        return wallet.getBalance();
    }

    @Transactional
    public void deductAmount(Long userId, Double amount){
        Wallet w = walletRepository.findById(userId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        if (w.getBalance() < amount) throw new RuntimeException("Insufficient balance");
        w.setBalance(w.getBalance() - amount);
        walletRepository.save(w);

        Transaction t = Transaction.builder()
                .userId(userId)
                .amount(-amount)
                .type(TransactionType.RIDE_PAYMENT)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(t);
    }

    @Transactional
    public void addtoUserWallet(Long userId, Double amount){
        Wallet w = walletRepository.findById(userId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        w.setBalance(w.getBalance() + amount);
        walletRepository.save(w);

        Transaction t = Transaction.builder()
                .userId(userId)
                .amount(amount)
                .type(TransactionType.RIDE_EARNING) // or TOP_UP when appropriate
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(t);
    }





}
