package com.example.groupsession.models;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends BaseAccount {
	public RegularAccount() {
		this.setBalance(500);
		this.setMinimumBalance(500);
		this.setInterestCharge(0);
		this.setPenalty(10);
	}
}
