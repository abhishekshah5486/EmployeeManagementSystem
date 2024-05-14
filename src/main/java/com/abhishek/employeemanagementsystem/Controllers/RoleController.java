package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Role;
import com.abhishek.employeemanagementsystem.Services.RoleService;
import com.abhishek.employeemanagementsystem.Services.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private ModelMapper modelMapper;
    private RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public RoleResponseDto createRole(@RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleService.createRole(roleRequestDto);
        RoleResponseDto createRoleResponseDto =  modelMapper.map(role, RoleResponseDto.class);
        createRoleResponseDto.setMessage("Role created successfully");
        return createRoleResponseDto;
    }

    @PutMapping("/{id}")
    public RoleResponseDto updateRole(@PathVariable("id") Long id, @RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleService.updateRole(id, roleRequestDto);
        RoleResponseDto roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
        roleResponseDto.setMessage("Role Updated successfully");
        return roleResponseDto;
    }

    @GetMapping("/{id}")
    public RoleResponseDto getRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRoleById(id);
        RoleResponseDto roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
        roleResponseDto.setMessage("Role Fetched successfully");
        return roleResponseDto;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id, @RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        roleService.deleteRole(id, adminLoginRequestDto);
    }

    @PostMapping("/employees/{employeeId}/roles/{roleId}/assign")
    public EmployeeResponseDto assignRoleToEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable Long roleId, @RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        Employee employee = roleService.assignRoleToEmployee(employeeId, roleId, adminLoginRequestDto);
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
        employeeResponseDto.setMessage("Role assigned successfully");
        return employeeResponseDto;
    }

    @PutMapping("/employees/{employeeId}/roles/{roleId}/assign")
    public EmployeeResponseDto changeRoleToEmployee(@PathVariable("employeeId") Long employeeId, @PathVariable Long roleId, @RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        Employee employee = roleService.changeRoleToEmployee(employeeId, roleId, adminLoginRequestDto);
        EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
        employeeResponseDto.setMessage("Role has been updated successfully");
        return employeeResponseDto;
    }

    @GetMapping("/employees/role/{roleId}")
    public List<EmployeeResponseDto> getAllEmployeesByRoleId(@PathVariable("roleId") Long roleId) {
        List<Employee> employees = roleService.getAllEmployeesByRoleId(roleId);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
            employeeResponseDtos.add(employeeResponseDto);
        }
        return employeeResponseDtos;
    }

}
