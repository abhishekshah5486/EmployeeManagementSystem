package com.abhishek.employeemanagementsystem.Exceptions;

public class NoTechnicalManagersFoundException extends RuntimeException {
    public NoTechnicalManagersFoundException(String message) {
        super(message);
    }
}
