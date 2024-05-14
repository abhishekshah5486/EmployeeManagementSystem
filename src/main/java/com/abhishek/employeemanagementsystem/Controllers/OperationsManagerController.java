package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations-managers")
public class OperationsManagerController {
    @Autowired
    private ModelMapper modelMapper;

    // CRUD OPERATIONS FOR OPERATIONS MANAGERS
    @PostMapping("/")
    public OperationsManager createOperationsManager(@RequestBody OperationsManager operationsManager) {
        return null;
    }

    @GetMapping("/{id}")
    public OperationsManager getOperationsManagerById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public OperationsManager updateOperationsManager(@PathVariable Long id, @RequestBody OperationsManager operationsManager) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOperationsManager(@PathVariable Long id) {

    }

}
