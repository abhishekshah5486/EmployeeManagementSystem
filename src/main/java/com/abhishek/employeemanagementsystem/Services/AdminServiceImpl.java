package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.AdminResetPasswordRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.*;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private DepartmentManagerServiceImpl departmentManagerService;
    private AdminRepository adminRepository;
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Override
    public Admin createAdmin(Admin admin) {
        // check if the username already exists
        Boolean isUsernameAlreadyPresent = adminRepository.existsByUsername(admin.getUsername());
        if (isUsernameAlreadyPresent) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
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

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        if (admins.isEmpty()){
            throw new NoAdminsFoundException("No admins found");
        }
        return admins;
    }

    @Override
    public Admin resetAdminPassword(AdminResetPasswordRequestDto adminResetPasswordRequestDto) {
        String username = adminResetPasswordRequestDto.getUsername();
        // validate if the username exists
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()){
            throw new InvalidUsernamePassedException("Invalid username !");
        }
        Admin adminToBeUpdated = optionalAdmin.get();
        adminToBeUpdated.setUsername(username);
        adminToBeUpdated.setPassword(adminResetPasswordRequestDto.getNewPassword());
        adminToBeUpdated.setId(optionalAdmin.get().getId());
        return adminRepository.save(adminToBeUpdated);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()){
            throw new NoAdminsFoundException("No admins found");
        }
        return optionalAdmin.get();
    }

    @Override
    public Admin adminLogin(AdminLoginRequestDto adminLoginRequestDto) {
        String username = adminLoginRequestDto.getUsername();
        String password = adminLoginRequestDto.getPassword();
        // Check if the username is valid
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()){
            throw new InvalidUsernamePassedException("Invalid username !");
        }
        // Check if the entered password is valid/invalid
        if (!optionalAdmin.get().getPassword().equals(password)){
            throw new InvalidPasswordForUsernameException("Invalid password !");
        }
        optionalAdmin.get().setLoginStatus(LoginStatus.LOGGEDIN);
        return adminRepository.save(optionalAdmin.get());
    }

    @Override
    public Admin adminLogout(Long id) {
        // Check if the passed admin id is valid
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found", id);
        }
        if (optionalAdmin.get().getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            return optionalAdmin.get();
        }
        optionalAdmin.get().setLoginStatus(LoginStatus.LOGGEDOUT);
        return adminRepository.save(optionalAdmin.get());
    }

    @Override
    public Admin assignAdminToDepartmentManager(Long adminId, Long departmentManagerId) {
        // Fetch Admin by Admin id
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found", adminId);
        }
        // Fetch DepartmentManager by Department Id
        DepartmentManager departmentManager = departmentManagerService.getDepartmentManagerById(departmentManagerId);
        optionalAdmin.get().setDepartmentManager(departmentManager);
        return adminRepository.save(optionalAdmin.get());
    }

    @Override
    public Admin updateAdminToDepartmentManager(Long adminId, Long departmentManagerId) {
        // Fetch Admin by Admin id
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isEmpty()){
            throw new AdminNotFoundException("Admin not found", adminId);
        }
        // Fetch DepartmentManager by Department Id
        DepartmentManager departmentManager = departmentManagerService.getDepartmentManagerById(departmentManagerId);
        optionalAdmin.get().setDepartmentManager(departmentManager);
        return adminRepository.save(optionalAdmin.get());
    }

    @Override
    public List<Admin> getAdminsByDepartmentManagerId(Long departmentManagerId) {
        List<Admin> allAdmins = adminRepository.findAll();
        if (allAdmins.isEmpty()){
            throw new NoAdminsFoundException("No admins found");
        }
        List<Admin> returnAdmins = new ArrayList<>();
        for (Admin admin : allAdmins){
            if (admin.getDepartmentManager().getId().equals(departmentManagerId)){
                returnAdmins.add(admin);
            }
        }
        return returnAdmins;
    }
}
