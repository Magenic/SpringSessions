package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.CheckingAccount;
import com.groupthree.bankapp.entity.InterestAccount;
import com.groupthree.bankapp.entity.RegularAccount;
import com.groupthree.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    public Account createAccount(String name, String accType) throws Exception {
        Account newAcc;
        switch (accType) {
            case "regular" : {
                newAcc = new RegularAccount();
                break;
            }
            case "checking" : {
                newAcc = new CheckingAccount();
                break;
            }
            case "interest" : {
                newAcc = new InterestAccount();
                break;
            }
            default: {
                throw new Exception("account type not supported.");
            }
        }
        newAcc.setName(name);
        // TODO: abstract random number generator for account number and fix formatting
        newAcc.setAcctNumber(String.valueOf((Math.floor(Math.random() * Math.pow(10, 15)))));
        repository.save(newAcc);
        return newAcc;
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountByAccNumber(String accNumber) throws Exception {
        return repository.findByAccountNumber(accNumber);
    }
}
