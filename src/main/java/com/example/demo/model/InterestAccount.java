package com.example.demo.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("interest")
public class InterestAccount extends Account {
	private double interestCharge;
	
	public InterestAccount() {
		setInterestCharge(0.3);
		setBalance(0.0);
	}

	public double getInterestCharge() {
		return interestCharge;
	}

	public void setInterestCharge(double interestCharge) {
		this.interestCharge = interestCharge;
	}
	
	
}
