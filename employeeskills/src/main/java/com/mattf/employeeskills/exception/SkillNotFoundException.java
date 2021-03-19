package com.mattf.employeeskills.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SkillNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SkillNotFoundException() {
        super("Skill not found.");
    }

    public SkillNotFoundException(Throwable t) {
        super("Skill not found.", t);
    }

}
