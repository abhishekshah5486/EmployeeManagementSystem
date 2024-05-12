package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Services.ProjectService;
import com.abhishek.employeemanagementsystem.Services.ProjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ModelMapper modelMapper;

    private ProjectServiceImpl projectService;
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }
}
