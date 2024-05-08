package com.abhishek.employeemanagementsystem.Exceptions;

public class InvalidPasswordForUsernameException extends RuntimeException {
    public InvalidPasswordForUsernameException(String message) {
        super(message);
    }
}
