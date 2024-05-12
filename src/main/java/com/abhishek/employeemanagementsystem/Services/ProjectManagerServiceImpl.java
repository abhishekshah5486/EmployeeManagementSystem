package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidProjectManagerIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoProjectManagersFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.ProjectManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import com.abhishek.employeemanagementsystem.Repositories.ProjectManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

    private ProjectManagerRepository projectManagerRepository;
    public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository) {
        this.projectManagerRepository = projectManagerRepository;
    }


    @Override
    public ProjectManager createProjectManager(CreateProjectManagerRequestDto createProjectManagerRequestDto) {
        ProjectManager projectManager = new ProjectManager();
        projectManager.setName(createProjectManagerRequestDto.getName());
        projectManager.setEmail(createProjectManagerRequestDto.getEmail());
        // Validate the username first and then create Project Manager
        projectManager.setUsername(createProjectManagerRequestDto.getUsername());
        projectManager.setPassword(createProjectManagerRequestDto.getPassword());
        projectManager.setDateOfJoining(createProjectManagerRequestDto.getDateOfJoining());
        return projectManagerRepository.save(projectManager);
    }

    @Override
    public void deleteProjectManager(Long id) {
        Optional<ProjectManager> projectManager = projectManagerRepository.findById(id);
        if (projectManager.isEmpty()){
            throw new InvalidProjectManagerIDException("Invalid Project Manager Id", id);
        }
        projectManagerRepository.deleteById(id);
    }

    @Override
    public ProjectManager updateProjectManager(Long id, UpdateProjectManagerRequestDto updateProjectManagerRequestDto) {
        Optional<ProjectManager> projectManager = projectManagerRepository.findById(id);
        if (projectManager.isEmpty()){
            throw new InvalidProjectManagerIDException("Invalid Project Manager Id", id);
        }
        projectManager.get().setEmail(updateProjectManagerRequestDto.getEmail());
        projectManager.get().setName(updateProjectManagerRequestDto.getName());
        // Validate the username first
        projectManager.get().setUsername(updateProjectManagerRequestDto.getUsername());
        return projectManagerRepository.save(projectManager.get());
    }

    @Override
    public ProjectManager getProjectManagerById(Long id) {
        Optional<ProjectManager> projectManager = projectManagerRepository.findById(id);
        if (projectManager.isEmpty()){
            throw new ProjectManagerIDNotFoundException("Invalid Project Manager Id", id);
        }
        return projectManager.get();
    }

    @Override
    public List<ProjectManager> getAllProjectManagers() {
        List<ProjectManager> projectManagers = projectManagerRepository.findAll();
        if (projectManagers.isEmpty()){
            throw new NoProjectManagersFoundException("No Project Manager found");
        }
        return projectManagers;
    }
}
