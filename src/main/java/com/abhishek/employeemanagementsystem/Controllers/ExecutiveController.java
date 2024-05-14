package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Models.Executive;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/executives")
public class ExecutiveController {
    @Autowired
    private ModelMapper modelMapper;

    // CRUD OPERATIONS FOR EXECUTIVE MANAGERS
    @PostMapping("/")
    public Executive createExecutive(@RequestBody Executive executive) {
        return null;
    }

    @GetMapping("/{id}")
    public Executive getExecutiveById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public Executive updateExecutive(@PathVariable Long id, @RequestBody Executive executive) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteExecutive(@PathVariable Long id) {

    }

}
