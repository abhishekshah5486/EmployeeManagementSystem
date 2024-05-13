package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Models.ProjectStatus;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {
    Project createProject(CreateProjectRequestDto createProjectRequestDto);
    Project updateProject(Long id, UpdateProjectRequestDto updateProjectRequestDto);
    void deleteProject(Long id);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
    List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate);
    List<Project> getProjectsByStartDate(LocalDate startDate);
    List<Project> getProjectsByEndDate(LocalDate endDate);
    List<Project> getProjectsByKeyword(String keyword);
    Project updateProjectStatus(Long id, String newStatus);
    Project assignEmployeeToProject(Long projectId, Long employeeId);
    Project removeEmployeeFromProject(Long projectId, Long employeeId);
    List<Employee> getAllEmployeesAssignedToProject(Long projectId);
}
