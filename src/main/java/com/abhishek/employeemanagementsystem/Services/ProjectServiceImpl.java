package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidProjectIDFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoProjectsFoundException;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(CreateProjectRequestDto createProjectRequestDto) {
        Project project = new Project();
        project.setTitle(createProjectRequestDto.getTitle());
        project.setDescription(createProjectRequestDto.getDescription());
        project.setStartDate(createProjectRequestDto.getStartDate());
        project.setProjectStatus(createProjectRequestDto.getProjectStatus());
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, UpdateProjectRequestDto updateProjectRequestDto) {
        // Validate the project id
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", id);
        }
        project.get().setTitle(updateProjectRequestDto.getTitle());
        project.get().setDescription(updateProjectRequestDto.getDescription());
        project.get().setStartDate(updateProjectRequestDto.getStartDate());
        project.get().setEndDate(updateProjectRequestDto.getEndDate());
        return projectRepository.save(project.get());
    }

    @Override
    public void deleteProject(Long id) {
        // Validate the project id
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Long id) {
        // Check if the project id is valid
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()){
            throw new NoProjectsFoundException("No Projects Found !", id);
        }
        return optionalProject.get();
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
       if (projects.isEmpty()) {
           throw new NoProjectsFoundException("No Projects Found !", 1L);
       }
       return projects;
    }

}
