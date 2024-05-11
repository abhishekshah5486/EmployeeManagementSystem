package com.abhishek.employeemanagementsystem.Exceptions;

public class NoTeamLeadersFoundException extends RuntimeException {
    public NoTeamLeadersFoundException(String message) {
        super(message);
    }
}
