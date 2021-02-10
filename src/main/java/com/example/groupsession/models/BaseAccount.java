package com.example.groupsession.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
	@Type(value = RegularAccount.class, name = "regular"),
	@Type(value = CheckingAccount.class, name = "checking"),
	@Type(value = InterestAccount.class, name = "interest")
})
public abstract class BaseAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String acctNumber;
	private double balance;
	private double minimumBalance;
	private double penalty;
	private double transactionCharge;
	private double interestCharge;
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public double getInterestCharge() {
		return interestCharge;
	}

	public void setInterestCharge(double interestCharge) {
		this.interestCharge = interestCharge;
	}

	public void setName(String name) {
		this.name = name;
	}
}
