package com.abhishek.employeemanagementsystem.Exceptions;

public class AdminAlreadyInTeamException extends RuntimeException {
    public AdminAlreadyInTeamException(String message) {
        super(message);
    }
}
