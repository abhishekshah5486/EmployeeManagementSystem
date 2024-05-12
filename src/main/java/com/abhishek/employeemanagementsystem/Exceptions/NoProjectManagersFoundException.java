package com.abhishek.employeemanagementsystem.Exceptions;

public class NoProjectManagersFoundException extends RuntimeException {
    public NoProjectManagersFoundException(String message) {
        super(message);
    }
}
