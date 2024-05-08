package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Services.DepartmentService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private ModelMapper modelMapper;
    private DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/")
    public Department createDepartment(@RequestBody Department department, AdminLoginRequestDto adminLoginRequestDto) {
        return departmentService.createDepartment(department, adminLoginRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id, AdminLoginRequestDto adminLoginRequestDto) {
        departmentService.deleteDepartment(id, adminLoginRequestDto);
    }

    @PutMapping({"/{id}"})
    public Department updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateRequestDto departmentUpdateRequestDto, AdminLoginRequestDto adminLoginRequestDto) {
        return null;
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return null;
    }
}
