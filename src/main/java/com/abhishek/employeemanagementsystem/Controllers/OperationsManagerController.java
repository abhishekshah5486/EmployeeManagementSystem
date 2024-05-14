package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.OperationsManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import com.abhishek.employeemanagementsystem.Services.OperationsManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/operations-managers")
public class OperationsManagerController {
    @Autowired
    private ModelMapper modelMapper;

    private OperationsManagerService operationsManagerService;
    public OperationsManagerController(OperationsManagerService operationsManagerService) {
        this.operationsManagerService = operationsManagerService;
    }

    // CRUD OPERATIONS FOR OPERATIONS MANAGERS
    @PostMapping("/")
    public OperationsManagerResponseDto createOperationsManager(@RequestBody CreateOperationsManagerRequestDto operationsManagerRequestDto) {
        OperationsManager operationsManager = operationsManagerService.createOperationsManager(operationsManagerRequestDto);
        OperationsManagerResponseDto operationsManagerResponseDto = modelMapper.map(operationsManager, OperationsManagerResponseDto.class);
        operationsManagerResponseDto.setMessage("Operations Manager created successfully.");
        return operationsManagerResponseDto;
    }

    @GetMapping("/{id}")
    public OperationsManagerResponseDto getOperationsManagerById(@PathVariable Long id) {
        OperationsManager operationsManager = operationsManagerService.getOperationsManagerById(id);
        OperationsManagerResponseDto operationsManagerResponseDto = modelMapper.map(operationsManager, OperationsManagerResponseDto.class);
        operationsManagerResponseDto.setMessage("Operations Manager retrieved successfully.");
        return operationsManagerResponseDto;
    }

    @PutMapping("/{id}")
    public OperationsManagerResponseDto updateOperationsManager(@PathVariable Long id, @RequestBody UpdateOperationsManagerRequestDto updateOperationsManagerRequestDto) {
        OperationsManager operationsManager = operationsManagerService.updateOperationsManager(id, updateOperationsManagerRequestDto);
        OperationsManagerResponseDto operationsManagerResponseDto = modelMapper.map(operationsManager, OperationsManagerResponseDto.class);
        operationsManagerResponseDto.setMessage("Operations Manager updated successfully.");
        return operationsManagerResponseDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOperationsManager(@PathVariable Long id) {
        operationsManagerService.deleteOperationsManager(id);
        return ResponseEntity.ok("Operations Manager deleted successfully.");
    }

    // Retrieve all Operations Managers
    @GetMapping("/")
    public List<OperationsManagerResponseDto> getAllOperationsManagers() {
        List<OperationsManager> operationsManagers = operationsManagerService.getAllOperationsManagers();
        List<OperationsManagerResponseDto> operationsManagerResponseDtos = new ArrayList<>();
        for (OperationsManager operationsManager : operationsManagers) {
            OperationsManagerResponseDto operationsManagerResponseDto = modelMapper.map(operationsManager, OperationsManagerResponseDto.class);
            operationsManagerResponseDto.setMessage("Operations Manager retrieved successfully.");
            operationsManagerResponseDtos.add(operationsManagerResponseDto);
        }
        return operationsManagerResponseDtos;
    }

    // Assigning / Adding Department to Operations Manager
    @PostMapping("/{operationsManagerId}/department/{departmentId}")
    public ResponseEntity<String> assignDepartmentToOperationsManager(@PathVariable Long operationsManagerId, @PathVariable Long departmentId) {
        operationsManagerService.assignDepartmentToOperationsManager(operationsManagerId, departmentId);
        return ResponseEntity.ok("Operations Manager assigned to department: " + operationsManagerId + " to department: " + departmentId);
    }

    // Updating Department of Operations Manager
    @PutMapping("/{operationsManagerId}/department/{departmentId}")
    public ResponseEntity<String> updateOperationsManagerDepartment(@PathVariable Long operationsManagerId, @PathVariable Long departmentId) {
        operationsManagerService.updateOperationsManagerDepartment(operationsManagerId, departmentId);
        return ResponseEntity.ok("Operations Manager updated successfully.");
    }

//     Assign Operations Manager to an Executive
    @PostMapping("/{operationsManagerId}/executive/{executiveId}")
    public ResponseEntity<String> assignOperationsManagerToExecutive(@PathVariable Long operationsManagerId, @PathVariable Long executiveId){
        operationsManagerService.assignOperationsManagerToExecutive(operationsManagerId, executiveId);
        return ResponseEntity.ok("Operations Manager Assigned to Executive Successfully");
    }

    // Update Executive of Operations Manager
    @PutMapping("/{operationsManagerId}/executive/{executiveId}")
    public ResponseEntity<String> updateOperationsManagerToExecutive(@PathVariable Long operationsManagerId, @PathVariable Long executiveId){
        operationsManagerService.updateOperationsManagerToExecutive(operationsManagerId, executiveId);
        return ResponseEntity.ok("Operations Manager Executive Updated Successfully");
    }

    // Retrieve all operations managers by Executive ID
    @GetMapping("/executive/{executiveId}")
    public List<OperationsManagerResponseDto> getOperationsManagersByExecutive(@PathVariable Long executiveId){
        List<OperationsManager> retrievedOperationsManagers = operationsManagerService.getOperationsManagersByExecutiveId(executiveId);
        List<OperationsManagerResponseDto> operationsManagerResponseDtos = new ArrayList<>();
        for(OperationsManager operationsManager : retrievedOperationsManagers){
            operationsManagerResponseDtos.add(modelMapper.map(operationsManager, OperationsManagerResponseDto.class));
        }
        return operationsManagerResponseDtos;
    }

}
