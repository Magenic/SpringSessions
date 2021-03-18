package com.example.demo.model;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("regular")
public class RegularAccount extends Account {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5486763562440722977L;
	private double minimumBalance;
	private double penalty;
	
	public RegularAccount() {
		this.setBalance(500);
		this.setMinimumBalance(500.0);
		this.setPenalty(10.0);
	}
	
	public double getMinimumBalance() {
		return minimumBalance;
	}
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
	
}
