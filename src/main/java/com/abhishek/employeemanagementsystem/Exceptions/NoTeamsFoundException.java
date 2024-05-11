package com.abhishek.employeemanagementsystem.Exceptions;

public class NoTeamsFoundException extends RuntimeException {
    public NoTeamsFoundException(String message) {
        super(message);
    }
}
