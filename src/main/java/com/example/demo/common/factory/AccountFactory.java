package com.example.demo.common.factory;

import org.springframework.stereotype.Component;

import com.example.demo.common.AccountType;
import com.example.demo.exception.IncorrectAccountTypeException;
import com.example.demo.model.Account;
import com.example.demo.model.CheckingAccount;
import com.example.demo.model.InterestAccount;
import com.example.demo.model.RegularAccount;
import com.example.demo.request.AccountRequest;

@Component
public class AccountFactory {


	public Account getAccount(String accountType , AccountRequest accountRequest) throws IncorrectAccountTypeException{
		
		if (AccountType.compareAccountType(AccountType.CHECKING, accountType)) {
			CheckingAccount checkAccount = new CheckingAccount();
			checkAccount.setBalance(100);
			checkAccount.setMinimumBalance(100);
			checkAccount.setName(accountRequest.getName());
			checkAccount.setTransactionCharge(1);
			checkAccount.setPenalty(10);
			return checkAccount;
			
		} else if (AccountType.compareAccountType(AccountType.INTEREST, accountType)) {
			InterestAccount interestAccount = new InterestAccount();
			interestAccount.setName(accountRequest.getName());
			interestAccount.setInterestCharge(0.03);
			return interestAccount;
			
		} else if (AccountType.compareAccountType(AccountType.REGULAR, accountType)) {
			RegularAccount regularAccount = new RegularAccount();
			regularAccount.setBalance(500);
			regularAccount.setMinimumBalance(500);
			regularAccount.setName(accountRequest.getName());
			regularAccount.setPenalty(10);
			return regularAccount;
		}
		
		throw new IncorrectAccountTypeException("Invalid Account Type");
		
	}
	
	public Account getExistingAccountType(Account account, AccountRequest accountRequest) throws IncorrectAccountTypeException{
		
		if (AccountType.compareAccountType(AccountType.CHECKING, accountRequest.getType())) {
			CheckingAccount checkAccount = new CheckingAccount();
			checkAccount.setBalance(account.getBalance());
			checkAccount.setMinimumBalance(100);
			checkAccount.setName(accountRequest.getName());
			checkAccount.setTransactionCharge(1);
			checkAccount.setPenalty(10);
			checkAccount.setId(account.getId());
			return checkAccount;
			
		} else if (AccountType.compareAccountType(AccountType.INTEREST, accountRequest.getType())) {
			InterestAccount interestAccount = new InterestAccount();
			interestAccount.setName(accountRequest.getName());
			interestAccount.setInterestCharge(0.03);
			interestAccount.setBalance(account.getBalance());
			interestAccount.setId(account.getId());
			return interestAccount;
			
		} else if (AccountType.compareAccountType(AccountType.REGULAR, accountRequest.getType())) {
			RegularAccount regularAccount = new RegularAccount();
			regularAccount.setBalance(account.getBalance());
			regularAccount.setMinimumBalance(500);
			regularAccount.setName(accountRequest.getName());
			regularAccount.setPenalty(10);
			regularAccount.setId(account.getId());
			return regularAccount;
		}
		
		throw new IncorrectAccountTypeException("Invalid Account Type");
		
	}
}
