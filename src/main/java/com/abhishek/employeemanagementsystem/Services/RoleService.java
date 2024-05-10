package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleRequestDto;
import com.abhishek.employeemanagementsystem.Models.Role;

public interface RoleService {
    Role createRole(RoleRequestDto roleRequestDto);
    Role updateRole(Long id, RoleRequestDto roleRequestDto);
    Role getRoleById(Long id);
    void deleteRole(Long id, AdminLoginRequestDto adminLoginRequestDto);
}
