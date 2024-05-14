package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidProjectIDFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidProjectStatusFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoEmployeesAssignedToProjectIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoProjectsFoundException;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Models.ProjectStatus;
import com.abhishek.employeemanagementsystem.Models.Teams;
import com.abhishek.employeemanagementsystem.Repositories.ProjectRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectSearchService projectSearchService;
    @Autowired
    private EmployeeService employeeService;
//    @Autowired
//    private TeamServiceImpl teamServiceImpl;

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

        Project savedProject = projectRepository.save(project);
        // Insert the title into trie
        projectSearchService.insertIntoTrie(createProjectRequestDto.getTitle().toLowerCase(), savedProject.getId());
        return savedProject;
    }

    @Override
    public Project updateProject(Long id, UpdateProjectRequestDto updateProjectRequestDto) {
        // Validate the project id
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", id);
        }
        // Delete the old project title from trie
        projectSearchService.deleteFromTrie(project.get().getTitle());

        project.get().setTitle(updateProjectRequestDto.getTitle());
        project.get().setDescription(updateProjectRequestDto.getDescription());
        project.get().setStartDate(updateProjectRequestDto.getStartDate());
        project.get().setEndDate(updateProjectRequestDto.getEndDate());

        // Insert the new project title into trie
        projectSearchService.insertIntoTrie(updateProjectRequestDto.getTitle().toLowerCase(), project.get().getId());
        return projectRepository.save(project.get());
    }

    @Override
    public void deleteProject(Long id) {
        // Validate the project id
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", id);
        }

        // Delete the project title from trie
        projectSearchService.deleteFromTrie(project.get().getTitle());
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

    @Override
    public List<Project> getProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Project> projects = projectRepository.findProjectsByDateRange(startDate, endDate);
        if (projects.isEmpty()) {
            throw new NoProjectsFoundException("No Projects Found !", 1L);
        }
        return projects;
    }

    @Override
    public List<Project> getProjectsByStartDate(LocalDate startDate) {
        List<Project> projects = projectRepository.findProjectsByStartDate(startDate);
        if (projects.isEmpty()) {
            throw new NoProjectsFoundException("No Projects Found !", 1L);
        }
        return projects;
    }

    @Override
    public List<Project> getProjectsByEndDate(LocalDate endDate) {
       List<Project> projects = projectRepository.findProjectsByEndDate(endDate);
       if (projects.isEmpty()) {
           throw new NoProjectsFoundException("No Projects Found !", 1L);
       }
       return projects;
    }

    @Override
    public List<Project> getProjectsByKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        List<Long> projectIds = projectSearchService.prefixSearchIntoTrie(keyword);
        List<Project> projects = new ArrayList<>();
        if (projectIds.isEmpty()) {
            throw new NoProjectsFoundException("No Projects Found !", 1L);
        }
        for (Long projectId : projectIds) {
            Optional<Project> optionalProject = projectRepository.findById(projectId);
            if (optionalProject.isEmpty()) {
                throw new NoProjectsFoundException("No Projects Found !", projectId);
            }
            projects.add(optionalProject.get());
        }
        return projects;
    }

    @Override
    public Project updateProjectStatus(Long id, String newStatus) {
        // Fetch project by project id
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", id);
        }
        // Check if the project status is valid
        if (!EnumUtils.isValidEnum(ProjectStatus.class, newStatus)) {
            throw new InvalidProjectStatusFoundException("Invalid Project Status");
        }
        project.get().setProjectStatus(ProjectStatus.valueOf(newStatus));
        return projectRepository.save(project.get());
    }

    @Override
    public Project assignEmployeeToProject(Long projectId, Long employeeId) {
        // fetch project by project id
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", projectId);
        }
        List<Employee> employees = project.get().getEmployees();
        Employee employee = employeeService.getEmployeeById(employeeId);
        employees.add(employee);
        project.get().setEmployees(employees);
        return projectRepository.save(project.get());
    }

    @Override
    public Project removeEmployeeFromProject(Long projectId, Long employeeId) {
        // fetch project by project id
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", projectId);
        }
        List<Employee> employees = project.get().getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employeeId)) {
                employees.remove(i);
            }
        }
        project.get().setEmployees(employees);
        return projectRepository.save(project.get());
    }

    @Override
    public List<Employee> getAllEmployeesAssignedToProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()) {
            throw new InvalidProjectIDFoundException("Invalid Project ID", projectId);
        }
        List<Employee> employeeList = project.get().getEmployees();
        if (employeeList.isEmpty()) {
            throw new NoEmployeesAssignedToProjectIDException("No Employees Assigned To Project ID", projectId);
        }
        return employeeList;
    }

//    @Override
//    public Project assignProjectToTeam(Long projectId, Long teamId) {
//        // fetch project by project id
//        Optional<Project> project = projectRepository.findById(projectId);
//        if (project.isEmpty()) {
//            throw new InvalidProjectIDFoundException("Invalid Project ID", projectId);
//        }
//        // fetch team by team id
//        Teams team = teamServiceImpl.getTeamById(teamId);
//        project.get().setTeam(team);
//        return projectRepository.save(project.get());
//    }
//
//    @Override
//    public Project updateProjectTeam(Long projectId, Long teamId) {
//        // fetch project by project id
//        Optional<Project> project = projectRepository.findById(projectId);
//        if (project.isEmpty()) {
//            throw new InvalidProjectIDFoundException("Invalid Project ID", projectId);
//        }
//        // fetch team by team id
//        Teams team = teamServiceImpl.getTeamById(teamId);
//        project.get().setTeam(team);
//        return projectRepository.save(project.get());
//    }

}
