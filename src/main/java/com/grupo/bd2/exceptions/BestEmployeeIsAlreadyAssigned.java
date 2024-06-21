package com.grupo.bd2.exceptions;

public class BestEmployeeIsAlreadyAssigned extends RuntimeException {
    public BestEmployeeIsAlreadyAssigned(String message) {
        super(message);
    }
}
