package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.ProjectResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Services.ProjectService;
import com.abhishek.employeemanagementsystem.Services.ProjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ModelMapper modelMapper;

    private final ProjectServiceImpl projectService;
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    // Create a Project
    @PostMapping("/")
    public ProjectResponseDto createProject(@RequestBody CreateProjectRequestDto createProjectRequestDto) {
        Project project = projectService.createProject(createProjectRequestDto);
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        projectResponseDto.setMessage("Project created successfully");
        return projectResponseDto;
    }

    // Retrieve a Project by project id
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(@PathVariable Long id, UpdateProjectRequestDto updateProjectRequestDto){
        Project project = projectService.updateProject(id, updateProjectRequestDto);
        ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
        projectResponseDto.setMessage("Project updated successfully");
        return projectResponseDto;
    }

    // Delete an existing project
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project deleted successfully");
    }

    // Retrieve all the projects
    @GetMapping("/")
    public List<ProjectResponseDto> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDtos.add(projectResponseDto);
        }
        return projectResponseDtos;
    }
}
