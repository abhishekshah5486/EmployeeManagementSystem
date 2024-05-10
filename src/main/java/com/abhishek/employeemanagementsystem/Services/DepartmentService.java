package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Employee;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(CreateDepartmentRequestDto createDepartmentRequestDto);
    void deleteDepartment(Long id, AdminLoginRequestDto adminLoginRequestDto);
    Department updateDepartment(Long id, UpdateDepartmentRequestDto updateDepartmentRequestDto);
    Department getDepartmentById(Long id);
    List<Employee> getAllEmployeesByDepartmentId(Long id);
}
