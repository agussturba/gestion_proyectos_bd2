package com.grupo.bd2.exceptions;

public class EmployeeIsAlreadyAssignedException extends RuntimeException {
    public EmployeeIsAlreadyAssignedException(String message) {
        super(message);
    }
}
