package com.groupthree.bankapp.service;

import com.groupthree.bankapp.entity.Account;
import com.groupthree.bankapp.entity.CheckingAccount;
import com.groupthree.bankapp.entity.InterestAccount;
import com.groupthree.bankapp.entity.RegularAccount;
import com.groupthree.bankapp.exception.AccountNotFoundException;
import com.groupthree.bankapp.exception.TransactionTypeNotSupportedException;
import com.groupthree.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    AccountService service;

    public Account processTransaction(String transactionType, String acctId, double amount)
            throws TransactionTypeNotSupportedException, AccountNotFoundException {
        Account account = service.getAccountByAccNumber(acctId);
        double balance = account.getBalance();
        switch (transactionType) {
            case "deposit" : {
                account.setBalance((balance + amount));
                break;
            }
            case "withdrawal" : {
                account.setBalance((balance - amount));
                break;
            }
            default: {
                throw new TransactionTypeNotSupportedException();
            }
        }
        Account newAcc = service.updateAccount(account);
        return account;
    }
}
