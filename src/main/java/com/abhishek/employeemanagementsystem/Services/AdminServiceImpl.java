package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Exceptions.AdminNotFoundException;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public Admin createAdmin(Admin admin) {
        // check if the username already exists
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found", id);
        }
        return optionalAdmin.get();
    }
}
