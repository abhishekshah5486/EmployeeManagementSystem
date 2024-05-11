package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.TeamRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.TeamResponseDto;
import com.abhishek.employeemanagementsystem.Models.Teams;
import com.abhishek.employeemanagementsystem.Services.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public TeamResponseDto createTeam(@RequestBody TeamRequestDto teamRequestDto) {
        Teams team = teamService.createTeam(teamRequestDto);
        TeamResponseDto teamResponseDto =  modelMapper.map(team, TeamResponseDto.class);
        teamResponseDto.setMessage("Team created successfully");
        return teamResponseDto;
    }
}
