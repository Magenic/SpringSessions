package com.groupthree.bankapp.controller;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.AccountRequest;
import com.groupthree.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService service;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest account) throws Exception {
        Account newAcc = service.createAccount(account.getName(), account.getType());
        return new ResponseEntity<>(newAcc, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/{accId}")
    public ResponseEntity<Account> getAccountByAccId(@PathVariable String accId) throws Exception {
        Account acc = service.getAccountByAccNumber(accId);
        return new ResponseEntity<>(acc, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = service.getAllAccounts();
        return new ResponseEntity<>(accounts, new HttpHeaders(), HttpStatus.OK);
    }
}
