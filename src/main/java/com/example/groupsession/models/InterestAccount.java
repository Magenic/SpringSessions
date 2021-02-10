package com.example.groupsession.models;

import javax.persistence.Entity;

@Entity
public class InterestAccount extends BaseAccount {
	public InterestAccount() {
		this.setInterestCharge(0.03);
	}
}
