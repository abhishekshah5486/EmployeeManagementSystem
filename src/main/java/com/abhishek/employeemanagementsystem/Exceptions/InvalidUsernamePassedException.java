package com.abhishek.employeemanagementsystem.Exceptions;

public class InvalidUsernamePassedException extends RuntimeException {
    public InvalidUsernamePassedException(String message) {
        super(message);
    }
}
