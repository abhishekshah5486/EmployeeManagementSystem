package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.CreateRiskManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateProjectManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateRiskManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import com.abhishek.employeemanagementsystem.Models.RiskManager;

import java.util.List;

public interface RiskManagerService {
    RiskManager createRiskManager(CreateRiskManagerRequestDto createRiskManagerRequestDto);
    void deleteRiskManager(Long id);
    RiskManager updateRiskManager(Long id, UpdateRiskManagerRequestDto updateRiskManagerRequestDto);
    RiskManager getRiskManagerById(Long id);
    List<RiskManager> getAllRiskManagers();
}
