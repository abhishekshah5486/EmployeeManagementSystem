package com.abhishek.employeemanagementsystem.Exceptions;

public class NoDepartmentManagersFoundException extends RuntimeException {
    public NoDepartmentManagersFoundException(String message) {
        super(message);
    }
}
