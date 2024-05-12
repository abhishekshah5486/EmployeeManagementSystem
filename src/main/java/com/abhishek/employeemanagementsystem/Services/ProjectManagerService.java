package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Models.ProjectManager;

public interface ProjectManagerService {
    ProjectManager createProjectManager(ProjectManager projectManager);
    void deleteProjectManager(Long id);
}
