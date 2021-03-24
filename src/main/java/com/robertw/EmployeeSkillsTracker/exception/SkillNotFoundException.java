package com.robertw.EmployeeSkillsTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SkillNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String defaultMessage = "Skill was not found for employee";

    public SkillNotFoundException() {
        super(defaultMessage);
    }

    public SkillNotFoundException(Throwable t) {
        super(defaultMessage, t);
    }
}
