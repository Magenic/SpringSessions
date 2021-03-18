package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.TransactionType;
import com.example.demo.model.InterestAccount;
import com.example.demo.model.Transaction;
import com.example.demo.repository.InterestAccountRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class InterestAccountService {
	
	@Autowired
	private InterestAccountRepository interestAccountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository; 
	
	
	public void calculateAndUpdateBalance() {
		List<InterestAccount> interestAccountList = interestAccountRepository.findAllWithUpdatedDateBefore();
		List<Transaction> transactions = new ArrayList<Transaction>();
		if(interestAccountList!= null && !interestAccountList.isEmpty()) {
			interestAccountList.forEach(interestAccount -> {
				Transaction transaction = new Transaction();
				double newBalance = (interestAccount.getBalance() * interestAccount.getInterestCharge())+ interestAccount.getBalance();
				interestAccount.setBalance(newBalance);
				transaction.setAccount(interestAccount);
				transaction.setAmount(newBalance);
				transaction.setTransactionType(TransactionType.INTEREST);
				transactions.add(transaction);
				System.out.printf("added Interest for %s \n", interestAccount.getName());
			});
			
			if(!transactions.isEmpty()) {
				transactionRepository.saveAll(transactions);
			}
			
			interestAccountRepository.saveAll(interestAccountList);
		}
		
	}
	
}
