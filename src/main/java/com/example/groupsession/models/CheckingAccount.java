package com.example.groupsession.models;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends BaseAccount {
	public CheckingAccount() {
		this.setBalance(100);
		this.setMinimumBalance(100);
		this.setPenalty(10);
		this.setTransactionCharge(1);
	}
}
