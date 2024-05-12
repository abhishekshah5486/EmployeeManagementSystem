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
public class CreateFinanceManagerRequestDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfJoining;
}
