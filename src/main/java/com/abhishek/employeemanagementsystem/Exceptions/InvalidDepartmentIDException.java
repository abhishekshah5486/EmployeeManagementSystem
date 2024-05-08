package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidDepartmentIDException  extends RuntimeException{
    private Long id;
    public InvalidDepartmentIDException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
