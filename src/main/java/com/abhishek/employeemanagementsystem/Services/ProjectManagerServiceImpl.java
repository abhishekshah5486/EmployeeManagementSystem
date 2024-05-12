package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import com.abhishek.employeemanagementsystem.Repositories.ProjectManagerRepository;

public class ProjectManagerServiceImpl implements ProjectManagerService {

    private ProjectManagerRepository projectManagerRepository;
    public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository) {
        this.projectManagerRepository = projectManagerRepository;
    }

    @Override
    public ProjectManager createProjectManager(ProjectManager projectManager) {
        return null;
    }

    @Override
    public void deleteProjectManager(Long id) {

    }
}
