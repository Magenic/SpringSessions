package com.groupthree.bankapp.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountNumberGeneratorService {
    public String generate() {
        Random random = new Random();
        String accountNumber = "";
        int accountNumberDigits = 16;
        for (int i=0; i<accountNumberDigits; i++) {
            int randInt = random.nextInt(9);
            accountNumber += String.valueOf(randInt);
        }
        return accountNumber;
    }
}
