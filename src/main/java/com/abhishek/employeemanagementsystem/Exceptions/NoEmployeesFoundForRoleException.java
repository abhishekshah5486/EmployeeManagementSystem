package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoEmployeesFoundForRoleException extends RuntimeException {
    private Long id;
    private String name;
    public NoEmployeesFoundForRoleException(String message, Long id, String name) {
        super(message);
        this.id = id;
        this.name = name;
    }
}
