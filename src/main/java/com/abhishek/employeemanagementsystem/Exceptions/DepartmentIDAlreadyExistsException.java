package com.abhishek.employeemanagementsystem.Exceptions;

public class DepartmentIDAlreadyExistsException extends RuntimeException {
    public DepartmentIDAlreadyExistsException(String message) {
        super(message);
    }
}
