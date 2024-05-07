package com.abhishek.employeemanagementsystem.Dtos;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {
    private String name;
    private String email;
    private String departmentName;
    private String role;
    private LocalDate dateOfJoining;
}
