package com.grupo.bd2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(EmployeeIsAlreadyAssignedException.class)
    public ResponseEntity<?> handleEmployeeIsAlreadyAssignedException(EmployeeIsAlreadyAssignedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(BestEmployeeIsAlreadyAssigned.class)
    public ResponseEntity<?> handleBestEmployeeIsAlreadyAssignedException(BestEmployeeIsAlreadyAssigned e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
