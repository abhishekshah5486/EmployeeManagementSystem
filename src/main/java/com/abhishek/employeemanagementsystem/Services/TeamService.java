package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamRequestDto;
import com.abhishek.employeemanagementsystem.Models.Teams;

import java.util.List;

public interface TeamService {
    Teams createTeam(TeamRequestDto teamRequestDto);
    Teams getTeamById(Long id);
    List<Teams> retrieveAllTeams();
    void deleteTeamById(Long id);
    Teams assignTeamLeader(Long teamId, Long teamLeaderId);
    Teams assignEmployeeToTeam(Long teamId, Long employeeId);
    void deleteEmployeeFromTeam(Long teamId, Long employeeId);
    Teams assignAdminToTeam(Long teamId, Long adminId);
    void deleteAdminFromTeam(Long teamId, Long adminId);
    Teams assignDepartmentToTeam(Long teamId, Long departmentId);
    Teams updateTeamDepartment(Long teamId, Long departmentId);
    Teams aasignProjectManagerToTeam(Long teamId, Long projectManagerId);
    Teams updateProjectManagerToTeam(Long teamId, Long projectManagerId);
    void deleteProjectManagerFromTeam(Long teamId, Long projectManagerId);
    Teams assignMarketManagerToTeam(Long teamId, Long marketManagerId);
    Teams updateMarketManagerToTeam(Long teamId, Long marketManagerId);
    void deleteMarketManagerFromTeam(Long teamId, Long marketManagerId);
    Teams assignFinanceManagerToTeam(Long teamId, Long financeManagerId);
    Teams updateFinanceManagerToTeam(Long teamId, Long financeManagerId);
    void deleteFinanceManagerFromTeam(Long teamId, Long financeManagerId);
    Teams assignTechnicalManagerToTeam(Long teamId, Long technicalManagerId);
    Teams updateTechnicalManagerToTeam(Long teamId, Long technicalManagerId);
    void deleteTechnicalManagerFromTeam(Long teamId, Long technicalManagerId);
    Teams assignRiskManagerToTeam(Long teamId, Long riskManagerId);
    Teams updateRiskManagerToTeam(Long teamId, Long riskManagerId);
    void deleteRiskManagerFromTeam(Long teamId, Long riskManagerId);
}
