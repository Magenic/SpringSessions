package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = RegularAccount.class, name = "regular"),
		@JsonSubTypes.Type(value = CheckingAccount.class, name = "checking"),
		@JsonSubTypes.Type(value = InterestAccount.class, name = "interest") })
public abstract class Account extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6178772283947240065L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String accountNumber;
	private double balance;
	
	
	public Account() {
		Random random = new Random();
		String acctNumber = String.valueOf(random.nextDouble()).replace(".", "");
		setAccountNumber(acctNumber);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

}