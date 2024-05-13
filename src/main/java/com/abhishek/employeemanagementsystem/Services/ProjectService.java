package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Models.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(CreateProjectRequestDto createProjectRequestDto);
    Project updateProject(Long id, UpdateProjectRequestDto updateProjectRequestDto);
    void deleteProject(Long id);
    Project getProjectById(Long id);
    List<Project> getAllProjects();
}
