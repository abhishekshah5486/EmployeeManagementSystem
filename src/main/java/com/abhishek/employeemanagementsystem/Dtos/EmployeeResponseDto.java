package com.abhishek.employeemanagementsystem.Dtos;

import com.abhishek.employeemanagementsystem.Models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private String name;
    private String email;
    private String departmentName;
    private String teamName;
    private String roleName;
    private LocalDate dateOfJoining;
    private String message;

    public EmployeeResponseDto(Employee employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
//        this.departmentName = employee.getDepartment().getName();
//        this.roleName = employee.getRole().getName();
        this.dateOfJoining = employee.getDateOfJoining();
    }
}
