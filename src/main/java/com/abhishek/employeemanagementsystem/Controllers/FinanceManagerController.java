package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.FinanceManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.FinanceManager;
import com.abhishek.employeemanagementsystem.Services.FinanceManagerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/finance-managers")
public class FinanceManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private FinanceManagerServiceImpl financeManagerService;

    public FinanceManagerController(FinanceManagerServiceImpl financeManagerService) {
        this.financeManagerService = financeManagerService;
    }

    // Create a Finance Manager
    @PostMapping("/")
    public FinanceManagerResponseDto createFinanceManager(@RequestBody CreateFinanceManagerRequestDto createFinanceManagerRequestDto) {
        FinanceManager financeManager = financeManagerService.createFinanceManager(createFinanceManagerRequestDto);
        FinanceManagerResponseDto financeManagerResponseDto = modelMapper.map(financeManager, FinanceManagerResponseDto.class);
        financeManagerResponseDto.setMessage("Finance manager created successfully");
        return financeManagerResponseDto;
    }

    // Delete a Finance Manager
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFinanceManager(@PathVariable Long id) {
        financeManagerService.deleteFinanceManager(id);
        return ResponseEntity.ok("Finance manager deleted successfully");
    }

    // Update a Finance Manager
    @PutMapping("/{id}")
    public FinanceManagerResponseDto updateFinanceManager(@PathVariable Long id, @RequestBody UpdateFinanceManagerRequestDto updateFinanceManagerRequestDto) {
        FinanceManager financeManager = financeManagerService.updateFinanceManager(id, updateFinanceManagerRequestDto);
        FinanceManagerResponseDto financeManagerResponseDto = modelMapper.map(financeManager, FinanceManagerResponseDto.class);
        financeManagerResponseDto.setMessage("Finance manager updated successfully");
        return financeManagerResponseDto;
    }

    // Retrieve Finance Manager by Id
    @GetMapping("/{id}")
    public FinanceManagerResponseDto getFinanceManagerById(@PathVariable Long id) {
        FinanceManager financeManager = financeManagerService.getFinanceManagerById(id);
        FinanceManagerResponseDto financeManagerResponseDto = modelMapper.map(financeManager, FinanceManagerResponseDto.class);
        financeManagerResponseDto.setMessage("Finance manager retrieved successfully");
        return financeManagerResponseDto;
    }

    // Retrieve All Finance Managers
    @GetMapping("/")
    public List<FinanceManagerResponseDto> getAllFinanceManagers() {
        List<FinanceManager> financeManagers = financeManagerService.getAllFinanceManagers();
        List<FinanceManagerResponseDto> financeManagerResponseDtos = new ArrayList<>();
        for (FinanceManager financeManager : financeManagers) {
            FinanceManagerResponseDto financeManagerResponseDto = modelMapper.map(financeManager, FinanceManagerResponseDto.class);
            financeManagerResponseDto.setMessage("Finance manager retrieved successfully");
            financeManagerResponseDtos.add(financeManagerResponseDto); // Added this line to add the mapped DTO to the list
        }
        return financeManagerResponseDtos;
    }

}
