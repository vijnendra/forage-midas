package com.jpmc.midascore.controller;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BalanceController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam(value = "userId") Long userId) {

        // 1. Look up the user (Spring wraps the result in an Optional box)
        Optional<UserRecord> userOpt = userRepository.findById(userId);

        // 2. Check if the box has a user inside it
        if (userOpt.isPresent()) {
            return new Balance(userOpt.get().getBalance());
        } else {
            return new Balance(0f);
        }
    }
}