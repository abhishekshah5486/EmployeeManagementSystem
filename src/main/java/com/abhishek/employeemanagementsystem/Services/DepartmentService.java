package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Models.Department;

public interface DepartmentService {
    Department createDepartment(Department department, AdminLoginRequestDto adminLoginRequestDto);
    void deleteDepartment(Long id, AdminLoginRequestDto adminLoginRequestDto);
    Department updateDepartment(Long id, DepartmentUpdateRequestDto departmentUpdateRequestDto, AdminLoginRequestDto adminLoginRequestDto);
    Department getDepartmentById(Long id);
}
