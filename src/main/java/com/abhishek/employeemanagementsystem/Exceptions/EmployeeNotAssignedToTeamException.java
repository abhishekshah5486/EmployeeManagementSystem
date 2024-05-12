package com.abhishek.employeemanagementsystem.Exceptions;

public class EmployeeNotAssignedToTeamException extends RuntimeException {
    public EmployeeNotAssignedToTeamException(String message) {
        super(message);
    }
}
