package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.ProjectResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Models.ProjectStatus;
import com.abhishek.employeemanagementsystem.Services.ProjectService;
import com.abhishek.employeemanagementsystem.Services.ProjectServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // Retrieve Projects within a date range
    @GetMapping("/startDate/{startDate}/endDate/{endDate}")
    public List<ProjectResponseDto> getProjectsByDateRange(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        List<Project> projects = projectService.getProjectsByDateRange(startDate, endDate);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDtos.add(projectResponseDto);
        }
        return projectResponseDtos;
    }

    // Retrieve Projects by Start Date
    @GetMapping("/startDate/{startDate}")
    public List<ProjectResponseDto> getProjectsByStartDate(@PathVariable LocalDate startDate) {
        List<Project> projects = projectService.getProjectsByStartDate(startDate);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDtos.add(projectResponseDto);
        }
        return projectResponseDtos;
    }

    // Retrieve Projects By End Date
    @GetMapping("/endDate/{endDate}")
    public List<ProjectResponseDto> getProjectsByEndDate(@PathVariable LocalDate endDate) {
        List<Project> projects = projectService.getProjectsByEndDate(endDate);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDtos.add(projectResponseDto);
        }
        return projectResponseDtos;
    }

    // Search Projects By Keywords
    @GetMapping("/search/{keyword}")
    public List<ProjectResponseDto> getProjectsByKeyword(@PathVariable String keyword) {
        List<Project> projects = projectService.getProjectsByKeyword(keyword);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            ProjectResponseDto projectResponseDto = modelMapper.map(project, ProjectResponseDto.class);
            projectResponseDtos.add(projectResponseDto);
        }
        return projectResponseDtos;
    }

    // Change the project status
    @PutMapping("/{projectId}")
    public ResponseEntity<String> updateProjectStatus(@PathVariable Long projectId,
                                                      @RequestParam String newStatus) {
        projectService.updateProjectStatus(projectId, newStatus);
        return ResponseEntity.ok("Project status updated successfully");
    }

    // Assign / Adding an employee to a project
    @PostMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<String> assignEmployeeToProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        projectService.assignEmployeeToProject(projectId, employeeId);
        return ResponseEntity.ok("Employee assigned to project successfully");
    }

    // Removing an employee from a project
    @DeleteMapping("/{projectId}/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        projectService.removeEmployeeFromProject(projectId, employeeId);
        return ResponseEntity.ok("Employee removed from project successfully");
    }

    // Retrieve all employees assigned to the project
    @GetMapping("/{projectId}/")
    public List<EmployeeResponseDto> getAllEmployeesAssignedToProject(@PathVariable Long projectId) {
        List<Employee> employees = projectService.getAllEmployeesAssignedToProject(projectId);
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeResponseDto employeeResponseDto = modelMapper.map(employee, EmployeeResponseDto.class);
            employeeResponseDtos.add(employeeResponseDto);
        }
        return employeeResponseDtos;
    }

    // Assign /
}
