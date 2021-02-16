package com.groupthree.bankapp.entity;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends MinimumBalanceAccount {
    public CheckingAccount() {
        super();
        setBalance(100);
        setMinimumBalance(100);
        setInterestCharge(0);
        setTransactionCharge(1);
        setPenalty(10);
    }

    @Override
    public void setBalance(double balance) {
        balance = this.verifyWithinMinimum(balance);
        balance -= getTransactionCharge();
        this.balance = balance;
        this.verifyOverMinimum();
    }

}
