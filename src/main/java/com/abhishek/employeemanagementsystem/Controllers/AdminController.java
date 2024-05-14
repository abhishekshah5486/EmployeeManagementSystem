package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Services.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final ModelMapper modelMapper;
    private AdminService adminService;
    public AdminController(AdminService adminService, ModelMapper modelMapper) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/")
    public String createAdmin(@RequestBody Admin admin){
        adminService.createAdmin(admin);
        return "Admin Created Successfully";
    }

    @GetMapping("/{id}")
    public AdminResponseDto getAdminById(@PathVariable Long id){
        Admin admin =  adminService.getAdminById(id);
        return modelMapper.map(admin, AdminResponseDto.class);
    }

    @GetMapping("/")
    public List<AdminResponseDto> getAllAdmins(){
        List<Admin> allAdmins =  adminService.getAllAdmins();
        List<AdminResponseDto> adminResponseDtos = new ArrayList<>();
        for(Admin admin : allAdmins){
            adminResponseDtos.add(modelMapper.map(admin, AdminResponseDto.class));
        }
        return adminResponseDtos;
    }

    @PutMapping("/reset")
    public String resetAdminPassword(@RequestBody AdminResetPasswordRequestDto adminResetPasswordRequestDto){
        adminService.resetAdminPassword(adminResetPasswordRequestDto);
        return "Password reset successful. You can now log in with your new password.";
    }

    @PutMapping("/change/{id}")
    public String changeAdminPassword(@PathVariable Long id){
        return null;
    }

    // Get admin by username
    @GetMapping("/username")
    public AdminResponseDto getAdminByUsername(@RequestParam String username){
        Admin admin = adminService.getAdminByUsername(username);
        return modelMapper.map(admin, AdminResponseDto.class);
    }

    @PostMapping("/login")
    public AdminLoginResponseDto adminLogin(@RequestBody AdminLoginRequestDto adminLoginRequestDto){
        Admin adminLogin = adminService.adminLogin(adminLoginRequestDto);
        AdminLoginResponseDto adminLoginResponseDto = modelMapper.map(adminLogin, AdminLoginResponseDto.class);
        adminLoginResponseDto.setMessage("Login Successful");
        adminLoginResponseDto.setLoginStatus(LoginStatus.LOGGEDIN);
        return adminLoginResponseDto;
    }

    @PostMapping("/logout/{id}")
    public AdminLogoutResponseDto adminLogout(@PathVariable Long id){
        Admin admin = adminService.adminLogout(id);
        AdminLogoutResponseDto adminLogoutResponseDto = modelMapper.map(admin, AdminLogoutResponseDto.class);
        adminLogoutResponseDto.setMessage("Logout Successful");
        adminLogoutResponseDto.setLoginStatus(LoginStatus.LOGGEDOUT);
        return adminLogoutResponseDto;
    }

    // Assign Admin to a Department Manager
//    @PostMapping("/{adminId}/department-manager/{departmentManagerId}")
//    public ResponseEntity<String> assignAdminToDepartmentManager(@PathVariable Long adminId, @PathVariable Long departmentManagerId){
//        adminService.assignAdminToDepartmentManager(adminId, departmentManagerId);
//        return ResponseEntity.ok("Admin Assigned to Department Manager Successfully");
//    }
//
//    // Update Department Manager of Admin
//    @PutMapping("/{adminId}/department-manager/{departmentManagerId}")
//    public ResponseEntity<String> updateAdminToDepartmentManager(@PathVariable Long adminId, @PathVariable Long departmentManagerId){
//        adminService.updateAdminToDepartmentManager(adminId, departmentManagerId);
//        return ResponseEntity.ok("Admin Department Manager Updated Successfully");
//    }
//
//    // Retrieve all admins by DepartmentManagerID
//    @GetMapping("/department-manager/{departmentManagerId}")
//    public List<AdminResponseDto> getAdminsByDepartmentManager(@PathVariable Long departmentManagerId){
//        List<Admin> retrievedAdmins = adminService.getAdminsByDepartmentManagerId(departmentManagerId);
//        List<AdminResponseDto> adminResponseDtos = new ArrayList<>();
//        for(Admin admin : retrievedAdmins){
//            adminResponseDtos.add(modelMapper.map(admin, AdminResponseDto.class));
//        }
//        return adminResponseDtos;
//    }
}
