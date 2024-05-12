package com.abhishek.employeemanagementsystem.Exceptions;

public class NoMarketManagersFoundException extends RuntimeException {
    public NoMarketManagersFoundException(String message) {
        super(message);
    }
}
