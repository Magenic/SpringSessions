package com.example.demo.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("checking")
public class CheckingAccount extends Account{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513298178804288638L;
	private double minimumBalance;
	private double penalty;
	private double transactionCharge;
	
	public CheckingAccount() {
		this.setBalance(100);
		this.setMinimumBalance(100);
		this.setPenalty(10);
		this.setTransactionCharge(1);
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
	public double getTransactionCharge() {
		return transactionCharge;
	}
	public void setTransactionCharge(double transactionCharge) {
		this.transactionCharge = transactionCharge;
	}

}
