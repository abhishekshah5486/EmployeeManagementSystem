package com.abhishek.employeemanagementsystem.Exceptions;

public class NoEmployeesFoundException extends  RuntimeException{
    public NoEmployeesFoundException(String message) {
        super(message);
    }
}
