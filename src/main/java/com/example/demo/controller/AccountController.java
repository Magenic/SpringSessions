package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.factory.AccountFactory;
import com.example.demo.exception.IncorrectAccountTypeException;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.exception.InvalidAmoutException;
import com.example.demo.exception.InvalidTransactionTypeException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.request.AccountRequest;
import com.example.demo.request.TransactionRequest;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountFactory accountFactory;
	
	@PostMapping
	public Account createAccount(@RequestBody AccountRequest accountRequest) throws IncorrectAccountTypeException {
		Account account = accountFactory.getAccount(accountRequest.getType(),accountRequest);
		return accountService.createAccount(account);
	}
	
	@PutMapping("{accountId}")
	public Account updateAccount(@PathVariable("accountId") Long accountId ,@RequestBody AccountRequest accountRequest) throws IncorrectAccountTypeException, RecordNotFoundException {
		return accountService.updateAccount(accountId,accountRequest);
	}
	

	@GetMapping
	public Page<Account> getAllAccount(Pageable pageable) {
		Page<Account> page = accountService.getAccounts(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
		
		return new PageImpl<Account>(page.getContent(), pageable, page.getTotalElements()) {};
	}
	

	@GetMapping("{accountId}")
	public Account getAccountById(@PathVariable("accountId") Long accountId) throws RecordNotFoundException {
		return accountService.getAccount(accountId);
	}
	
	@DeleteMapping("{accountId}")
	public void deleteAccount(@PathVariable("accountId") Long accountId) throws RecordNotFoundException {
		 accountService.deleteAccount(accountId);
	}
	
	
	@PostMapping("{accountId}/transactions")
	public Account transactions(@PathVariable("accountId") Long accountId,
			@RequestBody TransactionRequest request) throws RecordNotFoundException, InvalidTransactionTypeException, InsufficientBalanceException, InvalidAmoutException {
		return accountService.transaction(accountId,request);
	}
	

	@GetMapping("{accountId}/transactions")
	public  Page<Transaction> getTransactionsByAccountId(@PathVariable("accountId") Long accountId,Pageable pageable) throws RecordNotFoundException {
		return accountService.getTransactionsByAccountId(accountId,pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort());
	}
	
	
}
