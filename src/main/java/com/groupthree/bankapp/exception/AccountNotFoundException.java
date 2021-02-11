package com.groupthree.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException() {
        super("Account not found exception");
    }

    public AccountNotFoundException(Throwable t) {
        super("Account not found exception", t);
    }

}
