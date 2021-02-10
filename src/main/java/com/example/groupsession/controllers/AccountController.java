package com.example.groupsession.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.groupsession.exceptions.InvalidAccountTypeException;
import com.example.groupsession.exceptions.InvalidTransactionTypeException;
import com.example.groupsession.exceptions.RecordNotFoundException;
import com.example.groupsession.models.BaseAccount;
import com.example.groupsession.models.CreateAccountRequest;
import com.example.groupsession.models.TransactionRequest;
import com.example.groupsession.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping()
	public ResponseEntity<List<BaseAccount>> get() {
		return new ResponseEntity<List<BaseAccount>>(service.get(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BaseAccount> get(@PathVariable("id") Long id) throws RecordNotFoundException {
		return new ResponseEntity<BaseAccount>(service.get(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BaseAccount> save(@RequestBody CreateAccountRequest request) throws InvalidAccountTypeException {
		return new ResponseEntity<BaseAccount>(service.create(request), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/transactions")
	public ResponseEntity<BaseAccount> update(@PathVariable("id") Long id, @RequestBody TransactionRequest request) throws InvalidTransactionTypeException, RecordNotFoundException {
		return new ResponseEntity<BaseAccount>(service.update(id, request), new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * The business will schedule this operation to be invoked monthly.
	 * This should be behind authentication.
	 * @return
	 */
	@PutMapping("/calculateInterest")
	public ResponseEntity<Void> calculateInterest() {
		service.calculateInterest();
		return ResponseEntity.ok().build();
	}
}
