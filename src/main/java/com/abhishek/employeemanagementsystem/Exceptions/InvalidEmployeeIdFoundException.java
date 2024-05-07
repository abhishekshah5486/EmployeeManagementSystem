package com.abhishek.employeemanagementsystem.Exceptions;

public class InvalidEmployeeIdFoundException extends RuntimeException {
    public InvalidEmployeeIdFoundException(String message) {
        super(message);
    }
}
