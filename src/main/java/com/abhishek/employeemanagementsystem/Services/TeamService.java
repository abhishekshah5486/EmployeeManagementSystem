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
}
