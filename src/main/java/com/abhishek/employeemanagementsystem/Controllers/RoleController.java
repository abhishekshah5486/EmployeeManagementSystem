package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleResponseDto;
import com.abhishek.employeemanagementsystem.Models.Role;
import com.abhishek.employeemanagementsystem.Services.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private ModelMapper modelMapper;
    private RoleServiceImpl roleServiceImpl;
    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @PostMapping("/")
    public RoleResponseDto createRole(@RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleServiceImpl.createRole(roleRequestDto);
        RoleResponseDto createRoleResponseDto =  modelMapper.map(role, RoleResponseDto.class);
        createRoleResponseDto.setMessage("Role created successfully");
        return createRoleResponseDto;
    }

    @PutMapping("/{id}")
    public RoleResponseDto updateRole(@PathVariable("id") Long id, @RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleServiceImpl.updateRole(id, roleRequestDto);
        RoleResponseDto roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
        roleResponseDto.setMessage("Role Updated successfully");
        return roleResponseDto;
    }

    @GetMapping("/{id}")
    public RoleResponseDto getRoleById(@PathVariable("id") Long id) {
        Role role = roleServiceImpl.getRoleById(id);
        RoleResponseDto roleResponseDto = modelMapper.map(role, RoleResponseDto.class);
        roleResponseDto.setMessage("Role Fetched successfully");
        return roleResponseDto;
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Long id, @RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        roleServiceImpl.deleteRole(id, adminLoginRequestDto);
    }
}
