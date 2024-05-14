package com.abhishek.employeemanagementsystem.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutiveResponseDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private LocalDate dateOfJoining;
    private String message;
}
