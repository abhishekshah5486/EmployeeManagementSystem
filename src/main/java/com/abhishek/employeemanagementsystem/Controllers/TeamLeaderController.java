package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.DeleteResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.TeamLeaderRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.TeamLeaderResponseDto;
import com.abhishek.employeemanagementsystem.Models.TeamLeader;
import com.abhishek.employeemanagementsystem.Services.TeamLeaderService;
import com.abhishek.employeemanagementsystem.Services.TeamLeaderServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team-leaders")
public class TeamLeaderController {
    @Autowired
    private ModelMapper modelMapper;

    private TeamLeaderServiceImpl teamLeaderServiceImpl;
    public TeamLeaderController(TeamLeaderServiceImpl teamLeaderServiceImpl) {
        this.teamLeaderServiceImpl = teamLeaderServiceImpl;
    }

    // Create Team Leader
    @PostMapping("/")
    public ResponseEntity<TeamLeaderResponseDto> createTeamLeader(@RequestBody TeamLeaderRequestDto teamLeaderRequestDto) {
        TeamLeader teamLeader = teamLeaderServiceImpl.createTeamLeader(teamLeaderRequestDto);
        TeamLeaderResponseDto responseDto = modelMapper.map(teamLeader, TeamLeaderResponseDto.class);
        responseDto.setMessage("Team leader created successfully");
        ResponseEntity<TeamLeaderResponseDto> teamLeaderResponseDto = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return teamLeaderResponseDto;
    }

    // Retrieve Team Leader by id
    @GetMapping("/{id}")
    public TeamLeaderResponseDto retrieveTeamLeaderById(@PathVariable Long id) {
        TeamLeader teamLeader = teamLeaderServiceImpl.getTeamLeaderById(id);
        TeamLeaderResponseDto  teamLeaderResponseDto = modelMapper.map(teamLeader, TeamLeaderResponseDto.class);
        teamLeaderResponseDto.setMessage("Team leader fetched successfully");
        return teamLeaderResponseDto;
    }

    // Retrieve All Team Leaders
    @GetMapping("/")
    public List<TeamLeaderResponseDto> retrieveAllTeamLeaders() {
        List<TeamLeader> teamLeaders = teamLeaderServiceImpl.getAllTeamLeaders();
        List<TeamLeaderResponseDto> teamLeaderResponseDtos = new ArrayList<>();
        for (TeamLeader teamLeader : teamLeaders) {
            teamLeaderResponseDtos.add(modelMapper.map(teamLeader, TeamLeaderResponseDto.class));
        }
        return teamLeaderResponseDtos;
    }

    // Update Team Leader
    @PutMapping("/{id}")
    public TeamLeaderResponseDto updateTeamLeader(@PathVariable Long id, @RequestBody TeamLeaderRequestDto teamLeaderRequestDto) {
        TeamLeader teamLeader = teamLeaderServiceImpl.updateTeamLeader(id, teamLeaderRequestDto);
        TeamLeaderResponseDto responseDto = modelMapper.map(teamLeader, TeamLeaderResponseDto.class);
        responseDto.setMessage("Team leader updated successfully");
        return responseDto;
    }

    // Delete the Team Leader
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponseDto> deleteTeamLeader(@PathVariable Long id) {
        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setMessage("Team leader deleted successfully");
        return new ResponseEntity<>(deleteResponseDto, HttpStatus.OK);
    }
}
