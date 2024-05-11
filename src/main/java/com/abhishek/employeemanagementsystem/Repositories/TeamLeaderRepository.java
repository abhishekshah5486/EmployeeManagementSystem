package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamLeaderRepository extends JpaRepository<TeamLeader, Long> {
    @Override
    Optional<TeamLeader> findById(Long id);

    @Override
    TeamLeader save(TeamLeader teamLeader);
}
