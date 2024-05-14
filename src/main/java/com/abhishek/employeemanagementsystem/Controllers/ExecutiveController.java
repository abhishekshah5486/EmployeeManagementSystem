package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.ExecutiveResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Models.Executive;
import com.abhishek.employeemanagementsystem.Services.ExecutiveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/executives")
public class ExecutiveController {
    @Autowired
    private ModelMapper modelMapper;

    private ExecutiveService executiveService;
    public ExecutiveController(ExecutiveService executiveService) {
        this.executiveService = executiveService;
    }

    // CRUD OPERATIONS FOR EXECUTIVES
    @PostMapping("/")
    public ExecutiveResponseDto createExecutive(@RequestBody CreateExecutiveRequestDto executiveRequestDto) {
        Executive executive = executiveService.createExecutive(executiveRequestDto);
        ExecutiveResponseDto executiveResponseDto = modelMapper.map(executive, ExecutiveResponseDto.class);
        executiveResponseDto.setMessage("Executive created successfully.");
        return executiveResponseDto;
    }

    @GetMapping("/{id}")
    public ExecutiveResponseDto getExecutiveById(@PathVariable Long id) {
        Executive executive = executiveService.getExecutiveById(id);
        ExecutiveResponseDto executiveResponseDto = modelMapper.map(executive, ExecutiveResponseDto.class);
        executiveResponseDto.setMessage("Executive retrieved successfully.");
        return executiveResponseDto;
    }

    @PutMapping("/{id}")
    public ExecutiveResponseDto updateExecutive(@PathVariable Long id, @RequestBody UpdateExecutiveRequestDto updateExecutiveRequestDto) {
        Executive executive = executiveService.updateExecutive(id, updateExecutiveRequestDto);
        ExecutiveResponseDto executiveResponseDto = modelMapper.map(executive, ExecutiveResponseDto.class);
        executiveResponseDto.setMessage("Executive updated successfully.");
        return executiveResponseDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExecutive(@PathVariable Long id) {
        executiveService.deleteExecutive(id);
        return ResponseEntity.ok("Executive deleted successfully.");
    }

    // Retrieve all Executives
    @GetMapping("/")
    public List<ExecutiveResponseDto> getAllExecutives() {
        List<Executive> executives = executiveService.getAllExecutives();
        List<ExecutiveResponseDto> executiveResponseDtos = new ArrayList<>();
        for (Executive executive : executives) {
            ExecutiveResponseDto executiveResponseDto = modelMapper.map(executive, ExecutiveResponseDto.class);
            executiveResponseDto.setMessage("Executive retrieved successfully.");
            executiveResponseDtos.add(executiveResponseDto);
        }
        return executiveResponseDtos;
    }

}
