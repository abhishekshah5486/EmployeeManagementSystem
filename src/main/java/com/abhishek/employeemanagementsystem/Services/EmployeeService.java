package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.EmployeeRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Models.Employee;

public interface EmployeeService {
    Employee getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, EmployeeUpdateRequestDto employeeUpdateRequestDto);
    void deleteEmployee(Long id);
}
