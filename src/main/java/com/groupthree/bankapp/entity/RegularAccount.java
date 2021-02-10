package com.groupthree.bankapp.entity;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends Account {
    public RegularAccount() {
        super();
        setBalance(500);
        setMinimumBalance(500);
        setInterestCharge(0);
    }

    @Override
    public void setBalance(double balance) {
        if (balance < getMinimumBalance()) {
            balance = balance - 10;
        }
        this.balance = balance;
    }
}
