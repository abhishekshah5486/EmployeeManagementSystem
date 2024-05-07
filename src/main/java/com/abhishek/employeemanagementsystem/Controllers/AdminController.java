package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.AdminCreateResponseDto;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Services.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/")
    public AdminCreateResponseDto createAdmin(@RequestBody Admin admin){
        adminService.createAdmin(admin);
        AdminCreateResponseDto adminCreateResponseDto = new AdminCreateResponseDto();
        adminCreateResponseDto.setMessage("Admin created successfully");
        return adminCreateResponseDto;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);
    }
}
