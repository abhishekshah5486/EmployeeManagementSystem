package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamLeaderRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidTeamLeaderIDException;
import com.abhishek.employeemanagementsystem.Models.TeamLeader;
import com.abhishek.employeemanagementsystem.Repositories.TeamLeaderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamLeaderServiceImpl implements TeamLeaderService {
    private TeamLeaderRepository teamLeaderRepository;
    public TeamLeaderServiceImpl(TeamLeaderRepository teamLeaderRepository) {
        this.teamLeaderRepository = teamLeaderRepository;
    }

    @Override
    public TeamLeader createTeamLeader(TeamLeaderRequestDto teamLeaderRequestDto) {
        TeamLeader teamLeader = new TeamLeader();
        teamLeader.setName(teamLeaderRequestDto.getName());
        teamLeader.setEmail(teamLeaderRequestDto.getEmail());
        teamLeader.setDateOfJoining(teamLeaderRequestDto.getDateOfJoining());
        return teamLeaderRepository.save(teamLeader);
    }

    @Override
    public TeamLeader getTeamLeaderById(Long id) {
        Optional<TeamLeader> teamLeader = teamLeaderRepository.findById(id);
        if (teamLeader.isEmpty()){
            throw new InvalidTeamLeaderIDException("Invalid TeamLeader id passed", id);
        }
        return teamLeader.get();
    }
}
