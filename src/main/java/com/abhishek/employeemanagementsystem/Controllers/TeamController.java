package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Models.Teams;
import com.abhishek.employeemanagementsystem.Services.TeamService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
//localhost:8080/teams
public class TeamController {
    @Autowired
    private ModelMapper modelMapper;

    private TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Create a new Team
    @PostMapping("/")
    public CreateTeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto) {
        Teams team = teamService.createTeam(teamRequestDto);
        CreateTeamResponseDto teamResponseDto =  modelMapper.map(team, CreateTeamResponseDto.class);
        teamResponseDto.setMessage("Team created successfully");
        return teamResponseDto;
    }

    // Retrieve Team Details By Id
    @GetMapping("/{id}")
    public TeamRetrieveResponseDto retrieveTeamById(@PathVariable Long id) {
        Teams team = teamService.getTeamById(id);
        TeamRetrieveResponseDto teamRetrieveResponseDto = modelMapper.map(team, TeamRetrieveResponseDto.class);
        if (team.getTeamLeader() != null) teamRetrieveResponseDto.setTeamLeaderResponseDto(modelMapper.map(team.getTeamLeader(), TeamLeaderResponseDto.class));
        if (team.getDepartment() != null) teamRetrieveResponseDto.setDepartmentResponseDto(modelMapper.map(team.getDepartment(), DepartmentResponseDto.class));
        if (team.getFinanceManager() != null) teamRetrieveResponseDto.setFinanceManagerResponseDto(modelMapper.map(team.getFinanceManager(), FinanceManagerResponseDto.class));
        if (team.getMarketManager() != null) teamRetrieveResponseDto.setMarketManagerResponseDto(modelMapper.map(team.getMarketManager(), MarketManagerResponseDto.class));
        if (team.getProjectManager() != null) teamRetrieveResponseDto.setProjectManagerResponseDto(modelMapper.map(team.getProjectManager(), ProjectManagerResponseDto.class));
        if (team.getRiskManager() != null) teamRetrieveResponseDto.setRiskManagerResponseDto(modelMapper.map(team.getRiskManager(), RiskManagerResponseDto.class));
        if (team.getTechnicalManager() != null) teamRetrieveResponseDto.setTechnicalManagerResponseDto(modelMapper.map(team.getTechnicalManager(), TechnicalManagerResponseDto.class));
        // Adding the list of Admins
        List<Admin> admins = team.getAdmins();
        List<AdminResponseDto> adminResponseDtos = new ArrayList<>();
        for (Admin admin : admins) {
            adminResponseDtos.add(modelMapper.map(admin, AdminResponseDto.class));
        }
        teamRetrieveResponseDto.setAdminResponseDtos(adminResponseDtos);
        // Adding the list of Projects
        List<Project> projects = team.getProjects();
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for (Project project : projects) {
            projectResponseDtos.add(modelMapper.map(project, ProjectResponseDto.class));
        }
        teamRetrieveResponseDto.setProjectResponseDtos(projectResponseDtos);
        // Adding the list of employees
        List<Employee> employees = team.getEmployees();
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();
        for (Employee employee : employees) {
            employeeResponseDtos.add(modelMapper.map(employee, EmployeeResponseDto.class));
        }
        teamRetrieveResponseDto.setEmployeeResponseDtos(employeeResponseDtos);
        return teamRetrieveResponseDto;
    }

    // Retrieve All Teams
    @GetMapping("/")
    public List<RetrieveAllTeamsResponseDto> retrieveAllTeams() {
        List<Teams> allTeams = teamService.retrieveAllTeams();
        List<RetrieveAllTeamsResponseDto> retrieveAllTeamsResponseDtos = new ArrayList<>();
        for (Teams team : allTeams) {
            retrieveAllTeamsResponseDtos.add(modelMapper.map(team, RetrieveAllTeamsResponseDto.class));
        }
        return retrieveAllTeamsResponseDtos;
    }

    // Delete a team by id
    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    // Assign a team leader to the existing team
    @PutMapping("/{teamId}/team-leader/{teamLeaderId}")
    public AssignTeamLeaderResponseDto assignTeamLeader(@PathVariable Long teamId, @PathVariable Long teamLeaderId) {
        teamService.assignTeamLeader(teamId, teamLeaderId);
        AssignTeamLeaderResponseDto assignTeamLeaderResponseDto = new AssignTeamLeaderResponseDto();
        assignTeamLeaderResponseDto.setTeamLeaderId(teamLeaderId);
        assignTeamLeaderResponseDto.setTeamId(teamId);
        assignTeamLeaderResponseDto.setMessage("Team leader assigned successfully");
        return assignTeamLeaderResponseDto;
    }

    // Assigning / Adding an Employee to the team
    @PostMapping("/{teamId}/employee/{employeeId}")
    public ResponseEntity<String> assignEmployeeToTeam(@PathVariable Long teamId, @PathVariable Long employeeId) {
        teamService.assignEmployeeToTeam(teamId, employeeId);
        return ResponseEntity.ok("Employee assigned to team successfully.");
    }

    // Remove Employee from a team
    @DeleteMapping("/{teamId}/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployeeFromTeam(@PathVariable Long teamId, @PathVariable Long employeeId) {
        teamService.deleteEmployeeFromTeam(teamId, employeeId);
        return ResponseEntity.ok("Employee removed from team successfully.");
    }

    // Assigning/ Adding Admin to the team
    @PostMapping("/{teamId}/admin/{adminId}")
    public ResponseEntity<String> assignAdminToTeam(@PathVariable Long teamId, @PathVariable Long adminId) {
        teamService.assignAdminToTeam(teamId, adminId);
        return ResponseEntity.ok("Admin assigned to team Id " + teamId + " successfully.");
    }

    // Removing an Admin From a Team
    @DeleteMapping("/{teamId}/admin/{adminId}")
    public ResponseEntity<String> deleteAdminFromTeam(@PathVariable Long teamId, @PathVariable Long adminId) {
        teamService.deleteAdminFromTeam(teamId, adminId);
        return ResponseEntity.ok("Admin removed from team successfully.");
    }

    // Assigning Team to a department
    @PostMapping("/{teamId}/department/{departmentId}")
    public ResponseEntity<String> assignDepartmentToTeam(@PathVariable Long teamId, @PathVariable Long departmentId) {
        teamService.assignDepartmentToTeam(teamId, departmentId);
        return ResponseEntity.ok("Department assigned to team Id " + teamId +  " successfully.");
    }

    // Updating Department of a team
    @PutMapping("/{teamId}/department/{departmentId}")
    public ResponseEntity<String> updateTeamDepartment(@PathVariable Long teamId, @PathVariable Long departmentId) {
        teamService.updateTeamDepartment(teamId, departmentId);
        return ResponseEntity.ok("Department updated successfully.");
    }
}
