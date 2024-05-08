package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.AdminResetPasswordRequestDto;
import com.abhishek.employeemanagementsystem.Models.Admin;

import java.util.List;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long id);
    List<Admin> getAllAdmins();
    Admin resetAdminPassword(AdminResetPasswordRequestDto adminResetPasswordRequestDto);
    Admin getAdminByUsername(String username);
    Admin adminLogin(AdminLoginRequestDto adminLoginRequestDto);
    Admin adminLogout(Long id);
}
