package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.TechnicalManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.TechnicalManager;
import com.abhishek.employeemanagementsystem.Services.TechnicalManagerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/technical-managers")
public class TechnicalManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private TechnicalManagerServiceImpl technicalManagerService;

    public TechnicalManagerController(TechnicalManagerServiceImpl technicalManagerService) {
        this.technicalManagerService = technicalManagerService;
    }

    // Create a Technical Manager
    @PostMapping("/")
    public TechnicalManagerResponseDto createTechnicalManager(@RequestBody CreateTechnicalManagerRequestDto createTechnicalManagerRequestDto) {
        TechnicalManager technicalManager = technicalManagerService.createTechnicalManager(createTechnicalManagerRequestDto);
        TechnicalManagerResponseDto technicalManagerResponseDto = modelMapper.map(technicalManager, TechnicalManagerResponseDto.class);
        technicalManagerResponseDto.setMessage("Technical manager created successfully");
        return technicalManagerResponseDto;
    }

    // Delete a Technical Manager
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTechnicalManager(@PathVariable Long id) {
        technicalManagerService.deleteTechnicalManager(id);
        return ResponseEntity.ok("Technical manager deleted successfully");
    }

    // Update a Technical Manager
    @PutMapping("/{id}")
    public TechnicalManagerResponseDto updateTechnicalManager(@PathVariable Long id, @RequestBody UpdateTechnicalManagerRequestDto updateTechnicalManagerRequestDto) {
        TechnicalManager technicalManager = technicalManagerService.updateTechnicalManager(id, updateTechnicalManagerRequestDto);
        TechnicalManagerResponseDto technicalManagerResponseDto = modelMapper.map(technicalManager, TechnicalManagerResponseDto.class);
        technicalManagerResponseDto.setMessage("Technical manager updated successfully");
        return technicalManagerResponseDto;
    }

    // Retrieve Technical Manager by Id
    @GetMapping("/{id}")
    public TechnicalManagerResponseDto getTechnicalManagerById(@PathVariable Long id) {
        TechnicalManager technicalManager = technicalManagerService.getTechnicalManagerById(id);
        TechnicalManagerResponseDto technicalManagerResponseDto = modelMapper.map(technicalManager, TechnicalManagerResponseDto.class);
        technicalManagerResponseDto.setMessage("Technical manager retrieved successfully");
        return technicalManagerResponseDto;
    }

    // Retrieve All Technical Managers
    @GetMapping("/")
    public List<TechnicalManagerResponseDto> getAllTechnicalManagers() {
        List<TechnicalManager> technicalManagers = technicalManagerService.getAllTechnicalManagers();
        List<TechnicalManagerResponseDto> technicalManagerResponseDtos = new ArrayList<>();
        for (TechnicalManager technicalManager : technicalManagers) {
            TechnicalManagerResponseDto technicalManagerResponseDto = modelMapper.map(technicalManager, TechnicalManagerResponseDto.class);
            technicalManagerResponseDto.setMessage("Technical manager retrieved successfully");
        }
        return technicalManagerResponseDtos;
    }

}
