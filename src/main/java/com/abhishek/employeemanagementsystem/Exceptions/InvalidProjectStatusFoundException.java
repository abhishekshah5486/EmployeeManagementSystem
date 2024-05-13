package com.abhishek.employeemanagementsystem.Exceptions;

public class InvalidProjectStatusFoundException extends RuntimeException {
    public InvalidProjectStatusFoundException(String message) {
        super(message);
    }
}
