package com.abhishek.employeemanagementsystem.Exceptions;

public class TeamAlreadyAssignedToDepartment extends RuntimeException {
    public TeamAlreadyAssignedToDepartment(String message) {
        super(message);
    }
}
