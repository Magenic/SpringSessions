package com.example.groupsession.factories;

import org.springframework.stereotype.Service;

import com.example.groupsession.exceptions.InvalidAccountTypeException;
import com.example.groupsession.models.BaseAccount;
import com.example.groupsession.models.CheckingAccount;
import com.example.groupsession.models.InterestAccount;
import com.example.groupsession.models.RegularAccount;

@Service
public class AccountFactory {
	public BaseAccount CreateAccount(String type) throws InvalidAccountTypeException {
		if (type.equals("regular")) {
			return new RegularAccount();
		} else if (type.equals("interest")) {
			return new InterestAccount();
		} else if (type.equals("checking")) {
			return new CheckingAccount();
		}
		throw new InvalidAccountTypeException("Unknown account type");
	}
}
