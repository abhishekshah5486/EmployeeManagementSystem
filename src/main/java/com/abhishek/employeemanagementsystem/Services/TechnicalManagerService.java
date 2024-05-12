package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.TechnicalManager;

import java.util.List;

public interface TechnicalManagerService {
    TechnicalManager createTechnicalManager(CreateTechnicalManagerRequestDto createTechnicalManagerRequestDto);
    void deleteTechnicalManager(Long id);
    TechnicalManager updateTechnicalManager(Long id, UpdateTechnicalManagerRequestDto updateTechnicalManagerRequestDto);
    TechnicalManager getTechnicalManagerById(Long id);
    List<TechnicalManager> getAllTechnicalManagers();

}
