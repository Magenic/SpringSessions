package com.groupthree.bankapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionTypeNotSupportedException extends Exception {
    private static final long serialVersionUID = 1L;

    public TransactionTypeNotSupportedException() {
        super("Transaction type not supported.");
    }

    public TransactionTypeNotSupportedException(Throwable t) {
        super("Transaction type not supported.", t);
    }

}
