package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.CheckingAccount;
import com.groupthree.bankapp.entity.InterestAccount;
import com.groupthree.bankapp.entity.RegularAccount;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    @Autowired
    AccountNumberGeneratorService accountNumberGeneratorService;

    public Account createAccount(String name, String accType) throws AccountTypeNotSupportedException {
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
                throw new AccountTypeNotSupportedException();
            }
        }
        newAcc.setName(name);
        newAcc.setAcctNumber(accountNumberGeneratorService.generate());
        repository.save(newAcc);
        return newAcc;
    }

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Account getAccountByAccNumber(String accNumber) throws AccountNotFoundException {
        return repository.findByAccountNumber(accNumber);
    }

    public Account updateAccount(Account account) {
        return repository.save(account);
    }

    public void deleteAccount(String accId) throws AccountNotFoundException {
        Account account = this.getAccountByAccNumber(accId);
        repository.delete(account);
    }
}
