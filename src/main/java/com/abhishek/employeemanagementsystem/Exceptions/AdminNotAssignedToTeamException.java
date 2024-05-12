package com.abhishek.employeemanagementsystem.Exceptions;

public class AdminNotAssignedToTeamException extends RuntimeException {
    public AdminNotAssignedToTeamException(String message) {
        super(message);
    }
}
