package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.ProjectManager;

import java.util.List;

public interface ProjectManagerService {
    ProjectManager createProjectManager(CreateProjectManagerRequestDto createProjectManagerRequestDto);
    void deleteProjectManager(Long id);
    ProjectManager updateProjectManager(Long id, UpdateProjectManagerRequestDto updateProjectManagerRequestDto);
    ProjectManager getProjectManagerById(Long id);
    List<ProjectManager> getAllProjectManagers();
}
