package com.groupthree.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.groupthree.bankapp.entity.CheckingAccount;
import com.groupthree.bankapp.entity.InterestAccount;
import com.groupthree.bankapp.entity.RegularAccount;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularAccount.class, name = "regular"),
        @JsonSubTypes.Type(value = CheckingAccount.class, name = "checking"),
        @JsonSubTypes.Type(value = InterestAccount.class, name = "interest")
})
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String acctNumber;

    protected double balance;

    private double minimumBalance;

    private boolean didPassMinimumBalance;

    private double penalty;

    private double transactionCharge;

    private double interestCharge;

    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    protected double getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    protected boolean isDidPassMinimumBalance() {
        return didPassMinimumBalance;
    }

    protected void setDidPassMinimumBalance(boolean didPassMinimumBalance) {
        this.didPassMinimumBalance = didPassMinimumBalance;
    }
    protected double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    protected double getTransactionCharge() {
        return transactionCharge;
    }

    public void setTransactionCharge(double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    protected double getInterestCharge() {
        return interestCharge;
    }

    public void setInterestCharge(double interestCharge) {
        this.interestCharge = interestCharge;
    }

    protected double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
