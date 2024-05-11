package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.TeamRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidTeamIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoTeamsFoundException;
import com.abhishek.employeemanagementsystem.Models.TeamLeader;
import com.abhishek.employeemanagementsystem.Models.Teams;
import com.abhishek.employeemanagementsystem.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamLeaderServiceImpl teamLeaderService;

    private TeamRepository teamRepository;
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Teams createTeam(TeamRequestDto teamRequestDto) {
        Teams team = new Teams();
        team.setName(teamRequestDto.getTeamName());
        team.setDescription(teamRequestDto.getTeamDescription());
        return teamRepository.save(team);
    }

    @Override
    public Teams getTeamById(Long id) {
        Optional<Teams> team = teamRepository.findById(id);
        if (team.isEmpty()){
            throw new InvalidTeamIDException("Invalid Team Id", id);
        }
        return team.get();
    }

    @Override
    public List<Teams> retrieveAllTeams() {
        List<Teams> teams = teamRepository.findAll();
        if (teams.isEmpty()){
            throw new NoTeamsFoundException("No teams found");
        }
        return teams;
    }

    @Override
    public void deleteTeamById(Long id) {
        Optional<Teams> team = teamRepository.findById(id);
        if (team.isEmpty()){
            throw new InvalidTeamIDException("Invalid Team Id", id);
        }
        teamRepository.deleteById(id);
    }

    @Override
    public Teams assignTeamLeader(Long teamId, Long teamLeaderId) {
        // fetch the team by id and team leader by id;
        Teams team = getTeamById(teamId);
        TeamLeader teamLeader = teamLeaderService.getTeamLeaderById(teamLeaderId);
        team.setTeamLeader(teamLeader);
        return teamRepository.save(team);
    }
}
