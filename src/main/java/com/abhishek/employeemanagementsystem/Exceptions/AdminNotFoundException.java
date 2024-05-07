package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminNotFoundException extends RuntimeException {
    private Long id;
    public AdminNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
