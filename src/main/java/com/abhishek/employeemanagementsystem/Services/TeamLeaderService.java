package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamLeaderRequestDto;
import com.abhishek.employeemanagementsystem.Models.TeamLeader;

public interface TeamLeaderService {
    TeamLeader createTeamLeader(TeamLeaderRequestDto teamLeaderRequestDto);
    TeamLeader getTeamLeaderById(Long id);
}
