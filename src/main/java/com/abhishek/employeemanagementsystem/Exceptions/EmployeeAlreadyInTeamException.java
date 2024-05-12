package com.abhishek.employeemanagementsystem.Exceptions;

public class EmployeeAlreadyInTeamException extends RuntimeException {
    public EmployeeAlreadyInTeamException(String message) {
        super(message);
    }
}
