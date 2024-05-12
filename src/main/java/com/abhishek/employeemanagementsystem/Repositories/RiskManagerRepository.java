package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.RiskManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RiskManagerRepository extends JpaRepository<RiskManager, Long> {
    @Override
    Optional<RiskManager> findById(Long id);

    @Override
    List<RiskManager> findAll();

    @Override
    RiskManager save(RiskManager riskManager);

    @Override
    void deleteById(Long id);
}
