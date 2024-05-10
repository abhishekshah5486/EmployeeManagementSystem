package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleRequestDto;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Role;

import java.util.List;

public interface RoleService {
    Role createRole(RoleRequestDto roleRequestDto);
    Role updateRole(Long id, RoleRequestDto roleRequestDto);
    Role getRoleById(Long id);
    void deleteRole(Long id, AdminLoginRequestDto adminLoginRequestDto);
    Employee assignRoleToEmployee(Long employeeId, Long roleId, AdminLoginRequestDto adminLoginRequestDto);
    Employee changeRoleToEmployee(Long employeeId, Long roleId, AdminLoginRequestDto adminLoginRequestDto);
    List<Employee> getAllEmployeesByRoleId(Long roleId);
}
