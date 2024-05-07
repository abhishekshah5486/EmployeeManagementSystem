package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.EmployeeRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
// This controller class can host HTTP API's
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return new EmployeeResponseDto(employee);
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeUpdateRequestDto employeeUpdateRequestDto) {
        Employee employee = employeeService.updateEmployee(id, employeeUpdateRequestDto);
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
