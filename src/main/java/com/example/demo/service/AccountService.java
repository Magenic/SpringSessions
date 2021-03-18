package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.common.TransactionType;
import com.example.demo.common.factory.AccountFactory;
import com.example.demo.exception.IncorrectAccountTypeException;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.exception.InvalidAmoutException;
import com.example.demo.exception.InvalidTransactionTypeException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.model.CheckingAccount;
import com.example.demo.model.RegularAccount;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.request.AccountRequest;
import com.example.demo.request.TransactionRequest;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountFactory accountFactory;
	

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account getAccount(Long accountId) throws RecordNotFoundException {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException("Account Not Found"));
	}

	public Page<Account> getAccounts(int pageNo,int pageSize ,Sort sort) {
		if(Optional.ofNullable(sort).isEmpty()) {
		sort = Sort.by("id").ascending();
		}
		
		Pageable paging = PageRequest.of(pageNo, pageSize,sort); 
		
		return accountRepository.findAll(paging);
	}

	public Account transaction(Long accountId, TransactionRequest request) throws RecordNotFoundException, InvalidTransactionTypeException, InsufficientBalanceException, InvalidAmoutException {
		Account account = getAccount(accountId);
		validateAmount(request);
		Transaction transaction = new Transaction();
		
		if (TransactionType.WITHDRAW.compareTransactionType(request.getType())) {
			double oldBalance = account.getBalance() - request.getAmount() ;
			transaction.setTransactionType(TransactionType.WITHDRAW);
			transaction.setAmount(request.getAmount());
			
			double newBalance = chargeOrPenalty(account,oldBalance, request.getAmount(),TransactionType.WITHDRAW);
			transaction.setCharges(oldBalance - newBalance);
			
			if (newBalance < 0)
				throw new InsufficientBalanceException("Insufficient Balance");
			account.setBalance(newBalance);

		}else if(TransactionType.DEPOSIT.compareTransactionType(request.getType())) {
			double oldBalance = account.getBalance() + request.getAmount();
			transaction.setTransactionType(TransactionType.DEPOSIT);
			transaction.setAmount(request.getAmount());
			
			double newBalance = chargeOrPenalty(account,oldBalance,request.getAmount(),TransactionType.DEPOSIT);
			transaction.setCharges(oldBalance - newBalance);
			account.setBalance(newBalance);
		
		}else {
			throw new InvalidTransactionTypeException();
		}
		  
		transaction.setAccount(account);
		transactionRepository.save(transaction);
		return accountRepository.save(account);
	}
	
	private void validateAmount(TransactionRequest transactionRequest) throws InvalidAmoutException {
		Double amount = transactionRequest.getAmount();
		if(amount == null || amount <= 0) {
			throw new InvalidAmoutException("Invalid Amount Provided");
		}
		
	}

	/**
	 * 
	 * 
	 * @param account
	 * @param amount
	 * @param transactionType
	 * @return
	 */
	
	private double chargeOrPenalty(Account account, double calculatedAmount,double transactionAmount , TransactionType transactionType) {
		if (account instanceof RegularAccount && transactionType.equals(TransactionType.WITHDRAW)) {
			RegularAccount regularAccount = (RegularAccount) account;

			if ((regularAccount.getBalance() - transactionAmount) < regularAccount.getMinimumBalance()) {
				calculatedAmount -= regularAccount.getPenalty();
				regularAccount.setPenalty(0);
			}
			
		}else if(account instanceof RegularAccount && transactionType.equals(TransactionType.DEPOSIT)) {
			RegularAccount regularAccount = (RegularAccount) account;

			if ((regularAccount.getBalance() + transactionAmount) >=  regularAccount.getMinimumBalance()) {
				 regularAccount.setPenalty(10);
			}
		}
		
		
		else if (account instanceof CheckingAccount && transactionType.equals(TransactionType.WITHDRAW)) {
			CheckingAccount checkingAccount = (CheckingAccount) account;

			if ((checkingAccount.getBalance() - (transactionAmount + checkingAccount.getTransactionCharge())) < checkingAccount.getMinimumBalance()) {
				calculatedAmount -= checkingAccount.getPenalty();
				checkingAccount.setPenalty(0);
			}
		
			calculatedAmount -= checkingAccount.getTransactionCharge();
		}
		else if (account instanceof CheckingAccount && transactionType.equals(TransactionType.DEPOSIT)) {
			CheckingAccount checkingAccount = (CheckingAccount) account;

			if ((checkingAccount.getBalance() + transactionAmount) > checkingAccount.getMinimumBalance()) {
				checkingAccount.setPenalty(10);
			}
		
			calculatedAmount -= checkingAccount.getTransactionCharge();
		}
		return calculatedAmount;
	}

	public void deleteAccount(Long accountId) throws RecordNotFoundException {
		Account account = getAccount(accountId);
		List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);
		transactionRepository.deleteAll(transactionList);
		accountRepository.delete(account);
	
	}

	public Page<Transaction> getTransactionsByAccountId(Long accountId, int pageNumber, int pageSize, Sort sort) {
		
		if(Optional.ofNullable(sort).isEmpty()) {
			sort = Sort.by("id").ascending();
			}
			
			Pageable paging = PageRequest.of(pageNumber, pageSize,sort); 
			
			return transactionRepository.findByAccountId(accountId, paging);
	}



	public Account updateAccount(Long accountId, AccountRequest accountRequest) throws RecordNotFoundException, IncorrectAccountTypeException {
		Account account = accountRepository.findById(accountId)
		.orElseThrow(() -> new RecordNotFoundException("Account Not Found"));

		account = accountFactory.getExistingAccountType(account, accountRequest);
		
		
		return accountRepository.save(account);
	}}
