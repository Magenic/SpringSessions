package com.groupthree.bankapp.controller;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.RegularAccount;
import com.groupthree.bankapp.entity.TransactionRequest;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.TransactionTypeNotSupportedException;
import com.groupthree.bankapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionService service;

    @PostMapping("/accounts/{accId}/transactions")
    public ResponseEntity<Account> processTransaction(@PathVariable String accId, @RequestBody TransactionRequest transactionRequest) throws TransactionTypeNotSupportedException, AccountNotFoundException {
        Account account = service.processTransaction(transactionRequest.getType(), accId, transactionRequest.getAmount());
        return new ResponseEntity<Account>(account, new HttpHeaders(), HttpStatus.OK);
    }
}
