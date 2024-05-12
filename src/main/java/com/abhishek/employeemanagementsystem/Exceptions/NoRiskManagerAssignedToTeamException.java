package com.abhishek.employeemanagementsystem.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoRiskManagerAssignedToTeamException extends RuntimeException {
    private Long id;
    public NoRiskManagerAssignedToTeamException(String message, Long id) {
        super(message);
        this.id = id;
    }
}
