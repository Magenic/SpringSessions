package com.robertw.EmployeeSkillsTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String defaultMessage = "Employee was not found";

    public EmployeeNotFoundException() {
        super(defaultMessage);
    }

    public EmployeeNotFoundException(Throwable t) {
        super(defaultMessage, t);
    }
}
