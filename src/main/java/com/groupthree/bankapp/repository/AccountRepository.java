package com.groupthree.bankapp.repository;

import com.groupthree.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    default Account findByAccountNumber(String accNumber) throws Exception {
        Optional<Account> account = this.findAll().stream().filter((acc)->acc.getAcctNumber().equals(accNumber)).findFirst();
        if (account.isPresent()) {
            return account.get();
        } else {
            // TODO: create custom exception
            throw new Exception("Account not found.");
        }
    }
}
