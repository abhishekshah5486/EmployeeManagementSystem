package com.abhishek.employeemanagementsystem.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department-managers")
public class DepartmentManagerController {
    @Autowired
    private ModelMapper modelMapper;


}
