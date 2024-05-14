package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveIDNotFoundException extends RuntimeException {
    private Long id;
    public ExecutiveIDNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
