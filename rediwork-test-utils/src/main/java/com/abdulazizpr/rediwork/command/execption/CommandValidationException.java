package com.abdulazizpr.rediwork.command.execption;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Getter
public class CommandValidationException extends RuntimeException {

    private Set<ConstraintViolation<?>> constraintViolations;

    @SuppressWarnings("unchecked")
    public CommandValidationException(Set constraintViolations) {
        this.constraintViolations = (Set<ConstraintViolation<?>>) constraintViolations;
    }

}
