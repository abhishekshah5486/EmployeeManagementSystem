package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.FinanceManager;

import java.util.List;

public interface FinanceManagerService {
    FinanceManager createFinanceManager(CreateFinanceManagerRequestDto createFinanceManagerRequestDto);
    void deleteFinanceManager(Long id);
    FinanceManager updateFinanceManager(Long id, UpdateFinanceManagerRequestDto updateFinanceManagerRequestDto);
    FinanceManager getFinanceManagerById(Long id);
    List<FinanceManager> getAllFinanceManagers();

}
