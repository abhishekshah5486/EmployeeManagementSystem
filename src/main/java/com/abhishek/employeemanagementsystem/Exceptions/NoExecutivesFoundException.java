package com.abhishek.employeemanagementsystem.Exceptions;

public class NoExecutivesFoundException extends RuntimeException {
    public NoExecutivesFoundException(String message) {
        super(message);
    }
}
