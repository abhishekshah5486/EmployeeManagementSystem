package com.abhishek.employeemanagementsystem.Exceptions;

public class EmployeeAlreadyInOtherTeamException extends RuntimeException {
    public EmployeeAlreadyInOtherTeamException(String message) {
        super(message);
    }
}
