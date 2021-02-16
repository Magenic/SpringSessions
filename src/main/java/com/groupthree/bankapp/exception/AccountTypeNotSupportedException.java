package com.groupthree.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountTypeNotSupportedException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountTypeNotSupportedException() {
        super("Account type not supported.");
    }

    public AccountTypeNotSupportedException(Throwable t) {
        super("Account type not supported.", t);
    }

}
