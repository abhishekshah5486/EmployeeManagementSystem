package com.abhishek.employeemanagementsystem.Exceptions;

public class NoOperationsManagersFoundException extends RuntimeException {
    public NoOperationsManagersFoundException(String message) {
        super(message);
    }
}
