package com.groupthree.bankapp.controller;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.AccountRequest;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.AccountTypeNotSupportedException;
import com.groupthree.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest account) throws AccountTypeNotSupportedException {
        Account newAcc = service.createAccount(account.getName(), account.getAccount());
        return new ResponseEntity<>(newAcc, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/{accId}")
    public ResponseEntity<Account> getAccountByAccId(@PathVariable String accId) throws AccountNotFoundException {
        Account acc = service.getAccountByAccNumber(accId);
        return new ResponseEntity<>(acc, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = service.getAllAccounts();
        return new ResponseEntity<>(accounts, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{accId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accId) throws AccountNotFoundException {
        service.deleteAccount(accId);
        return new ResponseEntity<String>("Account was deleted", new HttpHeaders(), HttpStatus.OK);
    }
}
