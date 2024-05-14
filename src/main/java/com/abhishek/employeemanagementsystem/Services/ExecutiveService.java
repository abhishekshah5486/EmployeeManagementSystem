package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Models.Executive;

import java.util.List;

public interface ExecutiveService {
    Executive createExecutive(CreateExecutiveRequestDto createExecutiveRequestDto);
    Executive updateExecutive(Long id, UpdateExecutiveRequestDto updateExecutiveRequestDto);
    void deleteExecutive(Long id);
    Executive getExecutiveById(Long id);
    List<Executive> getAllExecutives();

}
