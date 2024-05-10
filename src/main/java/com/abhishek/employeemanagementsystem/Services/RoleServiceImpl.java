package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.RoleNotFoundException;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Models.Role;
import com.abhishek.employeemanagementsystem.Repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    private AdminService adminService;
    public RoleServiceImpl(RoleRepository roleRepository, AdminService adminService) {
        this.roleRepository = roleRepository;
        this.adminService = adminService;
    }

    @Override
    public Role createRole(RoleRequestDto roleRequestDto) {
        Role role = roleRequestDto.getRole();
        AdminLoginRequestDto adminLoginRequestDto = roleRequestDto.getAdminLoginRequestDto();

        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDIN)){
            return roleRepository.save(role);
        }
        adminService.adminLogin(adminLoginRequestDto);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleRequestDto roleRequestDto) {
        AdminLoginRequestDto adminLoginRequestDto = roleRequestDto.getAdminLoginRequestDto();

        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDIN)){
            Role role = getRoleById(id);
            role.setName(roleRequestDto.getRole().getName());
            return roleRepository.save(role);
        }
        adminService.adminLogin(adminLoginRequestDto);
        Role role = getRoleById(id);
        role.setName(roleRequestDto.getRole().getName());
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()){
            throw new RoleNotFoundException("Invalid ID Passsed");
        }
        return role.get();
    }

    @Override
    public void deleteRole(Long id, AdminLoginRequestDto adminLoginRequestDto) {
        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDIN)){
           roleRepository.deleteById(id);
        }
        adminService.adminLogin(adminLoginRequestDto);
        roleRepository.deleteById(id);
    }
}
