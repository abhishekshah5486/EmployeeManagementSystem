package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentRequestDto;
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
    public Department createDepartment(@RequestBody CreateDepartmentRequestDto createDepartmentRequestDto) {
        return departmentService.createDepartment(createDepartmentRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id, AdminLoginRequestDto adminLoginRequestDto) {
        departmentService.deleteDepartment(id, adminLoginRequestDto);
    }

    @PutMapping({"/{id}"})
    public Department updateDepartment(@PathVariable Long id, @RequestBody UpdateDepartmentRequestDto updateDepartmentRequestDto) {
        return departmentService.updateDepartment(id, updateDepartmentRequestDto);
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }
}
