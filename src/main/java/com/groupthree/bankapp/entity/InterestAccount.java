package com.groupthree.bankapp.entity;

import javax.persistence.Entity;

@Entity
public class InterestAccount extends Account {
    public InterestAccount() {
        this(0.03);
    }

    public InterestAccount(double interestRate) {
        super();
        setInterestCharge(interestRate);
    }

}
