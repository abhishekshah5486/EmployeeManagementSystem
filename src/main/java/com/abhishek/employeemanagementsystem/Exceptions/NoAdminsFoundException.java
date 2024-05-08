package com.abhishek.employeemanagementsystem.Exceptions;

public class NoAdminsFoundException extends RuntimeException {
    public NoAdminsFoundException(String message) {
        super(message);
    }
}
