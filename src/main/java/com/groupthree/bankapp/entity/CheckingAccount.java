package com.groupthree.bankapp.entity;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends Account {
    public CheckingAccount() {
        super();
        setBalance(100);
        setMinimumBalance(100);
        setInterestCharge(0);
    }

    @Override
    public void setBalance(double balance) {
        if (balance < getMinimumBalance()) {
            balance = balance - 10;
        }
        balance--;
        this.balance = balance;
    }

}
