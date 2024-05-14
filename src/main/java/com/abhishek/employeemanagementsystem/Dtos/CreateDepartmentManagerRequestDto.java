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
public class CreateDepartmentManagerRequestDto{
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfJoining;
}
