package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentManagerRepository;
import com.abhishek.employeemanagementsystem.Services.DepartmentManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department-managers")
public class DepartmentManagerController {
    @Autowired
    private ModelMapper modelMapper;

    private DepartmentManagerService departmentManagerService;
    public DepartmentManagerController(DepartmentManagerService departmentManagerService) {
        this.departmentManagerService = departmentManagerService;
    }
    // CRUD OPERATIONS FOR DEPARTMENT MANAGERS
    @PostMapping("/")
    public DepartmentManagerResponseDto createDepartmentManager(@RequestBody CreateDepartmentManagerRequestDto departmentManagerRequestDto) {
        DepartmentManager departmentManager = departmentManagerService.createDepartmentManager(departmentManagerRequestDto);
        DepartmentManagerResponseDto departmentManagerResponseDto = modelMapper.map(departmentManager, DepartmentManagerResponseDto.class);
        departmentManagerResponseDto.setMessage("Department Manager created successfully.");
        return departmentManagerResponseDto;
    }

    @GetMapping("/{id}")
    public DepartmentManager getDepartmentManagerById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public DepartmentManager updateDepartmentManager(@PathVariable Long id, @RequestBody DepartmentManager departmentManager) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteDepartmentManager(@PathVariable Long id) {

    }
}
