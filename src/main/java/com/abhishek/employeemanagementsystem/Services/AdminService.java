package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Models.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long id);
}
