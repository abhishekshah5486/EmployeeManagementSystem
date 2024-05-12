package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Services.ProjectManagerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-managers")
public class ProjectManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private ProjectManagerServiceImpl projectManagerService;
    public ProjectManagerController(ProjectManagerServiceImpl projectManagerService) {
        this.projectManagerService = projectManagerService;
    }

    @PostMapping()
}
