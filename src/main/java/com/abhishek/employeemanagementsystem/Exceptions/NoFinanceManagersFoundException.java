package com.abhishek.employeemanagementsystem.Exceptions;

public class NoFinanceManagersFoundException extends RuntimeException {
    public NoFinanceManagersFoundException(String message) {
        super(message);
    }
}
