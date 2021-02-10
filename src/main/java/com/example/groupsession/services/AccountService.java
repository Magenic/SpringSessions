package com.example.groupsession.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groupsession.data.AccountRepository;
import com.example.groupsession.exceptions.InvalidAccountTypeException;
import com.example.groupsession.exceptions.InvalidTransactionTypeException;
import com.example.groupsession.exceptions.RecordNotFoundException;
import com.example.groupsession.factories.AccountFactory;
import com.example.groupsession.models.BaseAccount;
import com.example.groupsession.models.CreateAccountRequest;
import com.example.groupsession.models.TransactionRequest;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	@Autowired
	private AccountFactory factory;
	
	public List<BaseAccount> get() {
		return repository.findAll();
	}
	
	public BaseAccount get(Long id) throws RecordNotFoundException {
		Optional<BaseAccount> account = repository.findById(id);
		
		if (account.isPresent()) {
			return account.get();
		} else {
			throw new RecordNotFoundException("Account not found.");
		}
	}
	
	public BaseAccount create(CreateAccountRequest request) throws InvalidAccountTypeException {
		BaseAccount account = factory.CreateAccount(request.getType());
		account.setName(request.getName());
		account.setAcctNumber(String.valueOf((int)(Math.random() * 1000000000)));
		return repository.save(account);
	}
	
	public BaseAccount update(Long id, TransactionRequest request) throws RecordNotFoundException, InvalidTransactionTypeException {
		BaseAccount account = this.get(id);
		if (request.getType().equals("deposit")) {
			this.deposit(account, request.getAmount());
		} else if (request.getType().equals("withdraw")) {
			this.withdraw(account, request.getAmount());
		} else {
			throw new InvalidTransactionTypeException("Unknown transaction type");
		}
		return repository.save(account);
	}
	
	public void delete(Long id) throws RecordNotFoundException {
		Optional<BaseAccount> account = repository.findById(id);
		
		if (account.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Account not found.");
		}
	}
	
	private void deposit(BaseAccount account, double amount) {
		account.setBalance(account.getBalance() + amount - account.getTransactionCharge());
	}
	
	private void withdraw(BaseAccount account, double amount) {
		double newBalance = account.getBalance() - amount - account.getTransactionCharge();
		if (newBalance < account.getMinimumBalance()) {
			newBalance -= account.getPenalty();
		}
		account.setBalance(newBalance);
	}

	public void calculateInterest() {
		List<BaseAccount> accounts = this.get();
		accounts.stream().forEach(a -> {
			a.setBalance(a.getBalance() * (1.0 + a.getInterestCharge()));
			repository.save(a);
		});
	}
}
