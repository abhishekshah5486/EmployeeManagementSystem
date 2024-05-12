package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.*;
import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import com.abhishek.employeemanagementsystem.Models.RiskManager;
import com.abhishek.employeemanagementsystem.Services.ProjectManagerServiceImpl;
import com.abhishek.employeemanagementsystem.Services.RiskManagerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/risk-managers")
public class RiskManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private RiskManagerServiceImpl riskManagerService;

    public RiskManagerController(RiskManagerServiceImpl riskManagerService) {
        this.riskManagerService = riskManagerService;
    }

    // Create a Risk Manager
    @PostMapping("/")
    public RiskManagerResponseDto createRiskManager(@RequestBody CreateRiskManagerRequestDto createRiskManagerRequestDto) {
        RiskManager riskManager = riskManagerService.createRiskManager(createRiskManagerRequestDto);
        RiskManagerResponseDto riskManagerResponseDto = modelMapper.map(riskManager, RiskManagerResponseDto.class);
        riskManagerResponseDto.setMessage("Risk manager created successfully");
        return riskManagerResponseDto;
    }

    // Delete a Risk Manager
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRiskManager(@PathVariable Long id) {
        riskManagerService.deleteRiskManager(id);
        return ResponseEntity.ok("Risk manager deleted successfully");
    }

    // Update a Risk Manager
    @PutMapping("/{id}")
    public RiskManagerResponseDto updateRiskManager(@PathVariable Long id, @RequestBody UpdateRiskManagerRequestDto updateRiskManagerRequestDto) {
        RiskManager riskManager = riskManagerService.updateRiskManager(id, updateRiskManagerRequestDto);
        RiskManagerResponseDto riskManagerResponseDto = modelMapper.map(riskManager, RiskManagerResponseDto.class);
        riskManagerResponseDto.setMessage("Risk manager updated successfully");
        return riskManagerResponseDto;
    }

    // Retrieve Risk Manager by Id
    @GetMapping("/{id}")
    public RiskManagerResponseDto getRiskManagerById(@PathVariable Long id) {
        RiskManager riskManager = riskManagerService.getRiskManagerById(id);
        RiskManagerResponseDto riskManagerResponseDto = modelMapper.map(riskManager, RiskManagerResponseDto.class);
        riskManagerResponseDto.setMessage("Risk manager retrieved successfully");
        return riskManagerResponseDto;
    }

    // Retrieve All Risk Managers
    @GetMapping("/")
    public List<RiskManagerResponseDto> getAllRiskManagers() {
        List<RiskManager> riskManagers = riskManagerService.getAllRiskManagers();
        List<RiskManagerResponseDto> riskManagerResponseDtos = new ArrayList<>();
        for (RiskManager riskManager : riskManagers) {
            RiskManagerResponseDto riskManagerResponseDto = modelMapper.map(riskManager, RiskManagerResponseDto.class);
            riskManagerResponseDto.setMessage("Risk manager retrieved successfully");
            riskManagerResponseDtos.add(riskManagerResponseDto); // Added this line to add the mapped DTO to the list
        }
        return riskManagerResponseDtos;
    }

}
