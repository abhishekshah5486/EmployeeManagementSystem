package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department-managers")
public class DepartmentManagerController {
    @Autowired
    private ModelMapper modelMapper;

    // CRUD OPERATIONS FOR DEPARTMENT MANAGERS
    @PostMapping("/")
    public DepartmentManager createDepartmentManager(@RequestBody DepartmentManager departmentManager) {
        return null;
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
