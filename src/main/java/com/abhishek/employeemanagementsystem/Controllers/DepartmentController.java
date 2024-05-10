package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Services.DepartmentService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/{id}/employees")
    public List<EmployeeResponseDto> getAllEmployeesByDepartmentId(@PathVariable Long id) {
        List<Employee> employees = departmentService.getAllEmployeesByDepartmentId(id);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
            employeeResponseDtos.add(employeeResponseDto);
        }
        return employeeResponseDtos;
    }
}
