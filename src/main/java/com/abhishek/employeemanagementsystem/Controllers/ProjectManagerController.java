package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.ProjectManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import com.abhishek.employeemanagementsystem.Services.ProjectManagerServiceImpl;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project-managers")
public class ProjectManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private ProjectManagerServiceImpl projectManagerService;
    public ProjectManagerController(ProjectManagerServiceImpl projectManagerService) {
        this.projectManagerService = projectManagerService;
    }

    // Create a Project Manager
    @PostMapping("/")
    public ProjectManagerResponseDto createProjectManager(@RequestBody CreateProjectManagerRequestDto createProjectManagerRequestDto) {
        ProjectManager projectManager = projectManagerService.createProjectManager(createProjectManagerRequestDto);
        ProjectManagerResponseDto projectManagerResponseDto = modelMapper.map(projectManager, ProjectManagerResponseDto.class);
        projectManagerResponseDto.setMessage("Project manager created successfully");
        return projectManagerResponseDto;
    }

    // Delete a Project Manager
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProjectManager(@PathVariable Long id) {
        projectManagerService.deleteProjectManager(id);
        return ResponseEntity.ok("Project manager deleted successfully");
    }

    // Update a Project Manager
    @PutMapping("/{id}")
    public ProjectManagerResponseDto updateProjectManager(@PathVariable Long id, @RequestBody UpdateProjectManagerRequestDto updateProjectManagerRequestDto) {
        ProjectManager projectManager = projectManagerService.updateProjectManager(id, updateProjectManagerRequestDto);
        ProjectManagerResponseDto projectManagerResponseDto = modelMapper.map(projectManager, ProjectManagerResponseDto.class);
        projectManagerResponseDto.setMessage("Project manager updated successfully");
        return projectManagerResponseDto;
    }

    // Retrieve Project Manager by Id
    @GetMapping("/{id}")
    public ProjectManagerResponseDto getProjectManagerById(@PathVariable Long id) {
        ProjectManager projectManager = projectManagerService.getProjectManagerById(id);
        ProjectManagerResponseDto projectManagerResponseDto = modelMapper.map(projectManager, ProjectManagerResponseDto.class);
        projectManagerResponseDto.setMessage("Project manager retrieved successfully");
        return projectManagerResponseDto;
    }

    // Retrieve All Project Managers
    @GetMapping("/")
    public List<ProjectManagerResponseDto> getAllProjectManagers() {
        List<ProjectManager> projectManagers = projectManagerService.getAllProjectManagers();
        List<ProjectManagerResponseDto> projectManagerResponseDtos = new ArrayList<>();
        for (ProjectManager projectManager : projectManagers) {
            ProjectManagerResponseDto projectManagerResponseDto = modelMapper.map(projectManager, ProjectManagerResponseDto.class);
            projectManagerResponseDto.setMessage("Project manager retrieved successfully");
        }
        return projectManagerResponseDtos;
    }
}
