package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidTechnicalManagerIDException extends RuntimeException {
    private Long id;
    public InvalidTechnicalManagerIDException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
