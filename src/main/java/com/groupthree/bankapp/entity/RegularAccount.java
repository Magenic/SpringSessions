package com.groupthree.bankapp.entity;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends MinimumBalanceAccount {
    public RegularAccount() {
        super();
        setBalance(500);
        setMinimumBalance(500);
        setInterestCharge(0);
        setPenalty(10);
    }

    @Override
    public void setBalance(double balance) {
        balance = this.verifyWithinMinimum(balance);
        this.balance = balance;
        this.verifyOverMinimum();
    }
}
