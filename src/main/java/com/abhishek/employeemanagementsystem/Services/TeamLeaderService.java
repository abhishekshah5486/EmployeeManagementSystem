package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamLeaderRequestDto;
import com.abhishek.employeemanagementsystem.Models.TeamLeader;

import java.util.List;

public interface TeamLeaderService {
    TeamLeader createTeamLeader(TeamLeaderRequestDto teamLeaderRequestDto);
    TeamLeader getTeamLeaderById(Long id);
    List<TeamLeader> getAllTeamLeaders();
    TeamLeader updateTeamLeader(Long id, TeamLeaderRequestDto teamLeaderRequestDto);
    void deleteTeamLeader(Long id);
}
