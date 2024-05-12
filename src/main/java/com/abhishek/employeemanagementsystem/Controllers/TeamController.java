package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Project;
import com.abhishek.employeemanagementsystem.Models.Teams;
import com.abhishek.employeemanagementsystem.Services.TeamService;
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

    // Assigning Project Manager To Team
    @PostMapping("/{teamId}/project-manager/{projectManagerId}")
    public ResponseEntity<String> assignProjectManagerToTeam(@PathVariable Long teamId, @PathVariable Long projectManagerId) {
        teamService.aasignProjectManagerToTeam(teamId, projectManagerId);
        return ResponseEntity.ok("Project Manager assigned to team Id " + teamId + " successfully.");
    }

    // Updating Project Manager of a Team
    @PutMapping("/{teamId}/project-manager/{projectManagerId}")
    public ResponseEntity<String> updateProjectManagerToTeam(@PathVariable Long teamId, @PathVariable Long projectManagerId) {
        teamService.updateProjectManagerToTeam(teamId, projectManagerId);
        return ResponseEntity.ok("Project Manager updated successfully.");
    }

    // Removing Project Manager From a team
    @DeleteMapping("/{teamId}/project-manager/{projectManagerId}")
    public ResponseEntity<String> deleteProjectManagerFromTeam(@PathVariable Long teamId, @PathVariable Long projectManagerId) {
        teamService.deleteProjectManagerFromTeam(teamId, projectManagerId);
        return ResponseEntity.ok("Project Manager removed from team successfully.");
    }

    // Assigning Market Manager To Team
    @PostMapping("/{teamId}/market-manager/{marketManagerId}")
    public ResponseEntity<String> assignMarketManagerToTeam(@PathVariable Long teamId, @PathVariable Long marketManagerId) {
        teamService.assignMarketManagerToTeam(teamId, marketManagerId);
        return ResponseEntity.ok("Market Manager assigned to team Id " + teamId + " successfully.");
    }

    // Updating Market Manager of a Team
    @PutMapping("/{teamId}/market-manager/{marketManagerId}")
    public ResponseEntity<String> updateMarketManagerToTeam(@PathVariable Long teamId, @PathVariable Long marketManagerId) {
        teamService.updateMarketManagerToTeam(teamId, marketManagerId);
        return ResponseEntity.ok("Market Manager updated successfully.");
    }

    // Removing Market Manager From a team
    @DeleteMapping("/{teamId}/market-manager/{marketManagerId}")
    public ResponseEntity<String> deleteMarketManagerFromTeam(@PathVariable Long teamId, @PathVariable Long marketManagerId) {
        teamService.deleteMarketManagerFromTeam(teamId, marketManagerId);
        return ResponseEntity.ok("Market Manager removed from team successfully.");
    }

    // Assigning Finance Manager To Team
    @PostMapping("/{teamId}/finance-manager/{financeManagerId}")
    public ResponseEntity<String> assignFinanceManagerToTeam(@PathVariable Long teamId, @PathVariable Long financeManagerId) {
        teamService.assignFinanceManagerToTeam(teamId, financeManagerId);
        return ResponseEntity.ok("Finance Manager assigned to team Id " + teamId + " successfully.");
    }

    // Updating Finance Manager of a Team
    @PutMapping("/{teamId}/finance-manager/{financeManagerId}")
    public ResponseEntity<String> updateFinanceManagerToTeam(@PathVariable Long teamId, @PathVariable Long financeManagerId) {
        teamService.updateFinanceManagerToTeam(teamId, financeManagerId);
        return ResponseEntity.ok("Finance Manager updated successfully.");
    }

    // Removing Finance Manager From a team
    @DeleteMapping("/{teamId}/finance-manager/{financeManagerId}")
    public ResponseEntity<String> deleteFinanceManagerFromTeam(@PathVariable Long teamId, @PathVariable Long financeManagerId) {
        teamService.deleteFinanceManagerFromTeam(teamId, financeManagerId);
        return ResponseEntity.ok("Finance Manager removed from team successfully.");
    }

    // Assigning Technical Manager To Team
    @PostMapping("/{teamId}/technical-manager/{technicalManagerId}")
    public ResponseEntity<String> assignTechnicalManagerToTeam(@PathVariable Long teamId, @PathVariable Long technicalManagerId) {
        teamService.assignTechnicalManagerToTeam(teamId, technicalManagerId);
        return ResponseEntity.ok("Technical Manager assigned to team Id " + teamId + " successfully.");
    }

    // Updating Technical Manager of a Team
    @PutMapping("/{teamId}/technical-manager/{technicalManagerId}")
    public ResponseEntity<String> updateTechnicalManagerToTeam(@PathVariable Long teamId, @PathVariable Long technicalManagerId) {
        teamService.updateTechnicalManagerToTeam(teamId, technicalManagerId);
        return ResponseEntity.ok("Technical Manager updated successfully.");
    }

    // Removing Technical Manager From a team
    @DeleteMapping("/{teamId}/technical-manager/{technicalManagerId}")
    public ResponseEntity<String> deleteTechnicalManagerFromTeam(@PathVariable Long teamId, @PathVariable Long technicalManagerId) {
        teamService.deleteTechnicalManagerFromTeam(teamId, technicalManagerId);
        return ResponseEntity.ok("Technical Manager removed from team successfully.");
    }

    // Assigning Risk Manager To Team
    @PostMapping("/{teamId}/risk-manager/{riskManagerId}")
    public ResponseEntity<String> assignRiskManagerToTeam(@PathVariable Long teamId, @PathVariable Long riskManagerId) {
        teamService.assignRiskManagerToTeam(teamId, riskManagerId);
        return ResponseEntity.ok("Risk Manager assigned to team Id " + teamId + " successfully.");
    }

    // Updating Risk Manager of a Team
    @PutMapping("/{teamId}/risk-manager/{riskManagerId}")
    public ResponseEntity<String> updateRiskManagerToTeam(@PathVariable Long teamId, @PathVariable Long riskManagerId) {
        teamService.updateRiskManagerToTeam(teamId, riskManagerId);
        return ResponseEntity.ok("Risk Manager updated successfully.");
    }

    // Removing Risk Manager From a team
    @DeleteMapping("/{teamId}/risk-manager/{riskManagerId}")
    public ResponseEntity<String> deleteRiskManagerFromTeam(@PathVariable Long teamId, @PathVariable Long riskManagerId) {
        teamService.deleteRiskManagerFromTeam(teamId, riskManagerId);
        return ResponseEntity.ok("Risk Manager removed from team successfully.");
    }


}
