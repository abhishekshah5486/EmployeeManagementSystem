package com.abhishek.employeemanagementsystem.Exceptions;

public class NoRiskManagersFoundException extends RuntimeException {
    public NoRiskManagersFoundException(String message) {
        super(message);
    }
}
