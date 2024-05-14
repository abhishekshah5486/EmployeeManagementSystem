package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.OperationsManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentManagerRepository;
import com.abhishek.employeemanagementsystem.Services.DepartmentManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department-managers")
public class DepartmentManagerController {
    @Autowired
    private ModelMapper modelMapper;

    private DepartmentManagerService departmentManagerService;
    public DepartmentManagerController(DepartmentManagerService departmentManagerService) {
        this.departmentManagerService = departmentManagerService;
    }
    // CRUD OPERATIONS FOR DEPARTMENT MANAGERS
    @PostMapping("/")
    public DepartmentManagerResponseDto createDepartmentManager(@RequestBody CreateDepartmentManagerRequestDto departmentManagerRequestDto) {
        DepartmentManager departmentManager = departmentManagerService.createDepartmentManager(departmentManagerRequestDto);
        DepartmentManagerResponseDto departmentManagerResponseDto = modelMapper.map(departmentManager, DepartmentManagerResponseDto.class);
        departmentManagerResponseDto.setMessage("Department Manager created successfully.");
        return departmentManagerResponseDto;
    }

    @GetMapping("/{id}")
    public DepartmentManagerResponseDto getDepartmentManagerById(@PathVariable Long id) {
        DepartmentManager departmentManager = departmentManagerService.getDepartmentManagerById(id);
        DepartmentManagerResponseDto departmentManagerResponseDto = modelMapper.map(departmentManager, DepartmentManagerResponseDto.class);
        departmentManagerResponseDto.setMessage("Department Manager retrieved successfully.");
        return departmentManagerResponseDto;
    }

    @PutMapping("/{id}")
    public DepartmentManagerResponseDto updateDepartmentManager(@PathVariable Long id, @RequestBody UpdateDepartmentManagerRequestDto updateDepartmentManagerRequestDto) {
        DepartmentManager departmentManager = departmentManagerService.updateDepartmentManager(id, updateDepartmentManagerRequestDto);
        DepartmentManagerResponseDto departmentManagerResponseDto = modelMapper.map(departmentManager, DepartmentManagerResponseDto.class);
        departmentManagerResponseDto.setMessage("Department Manager updated successfully.");
        return departmentManagerResponseDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentManager(@PathVariable Long id) {
        departmentManagerService.deleteDepartmentManager(id);
        return ResponseEntity.ok("Department Manager deleted successfully.");
    }

    // Retrieve all Department Managers
    @GetMapping("/")
    public List<DepartmentManagerResponseDto> getAllDepartmentManagers() {
        List<DepartmentManager> departmentManagers = departmentManagerService.getAllDepartmentManagers();
        List<DepartmentManagerResponseDto> departmentManagerResponseDtos = new ArrayList<>();
        for (DepartmentManager departmentManager : departmentManagers) {
            DepartmentManagerResponseDto departmentManagerResponseDto = modelMapper.map(departmentManager, DepartmentManagerResponseDto.class);
            departmentManagerResponseDto.setMessage("Department Manager retrieved successfully.");
            departmentManagerResponseDtos.add(departmentManagerResponseDto);
        }
        return departmentManagerResponseDtos;
    }

    // Assigning / Adding Department to Department Manager
    @PostMapping("/{departmentManagerId}/department/{departmentId}")
    public ResponseEntity<String> assignDepartmentToDepartmentManager(@PathVariable Long departmentManagerId, @PathVariable Long departmentId) {
        departmentManagerService.assignDepartmentToDepartmentManager(departmentManagerId, departmentId);
        return ResponseEntity.ok("Department Manager assigned to department: " + departmentManagerId + " to department: " + departmentId);
    }

    // Updating Department of Department Manager
    @PutMapping("/{departmentManagerId}/department/{departmentId}")
    public ResponseEntity<String> updateDepartmentManagerDepartment(@PathVariable Long departmentManagerId, @PathVariable Long departmentId) {
        departmentManagerService.updateDepartmentManagerDepartment(departmentManagerId, departmentId);
        return ResponseEntity.ok("Department Manager updated successfully.");
    }

    // Assign Department Manager to an Operations Manager
    @PostMapping("/{departmentManagerId}/operations-manager/{operationsManagerId}")
    public ResponseEntity<String> assignDepartmentManagerToOperationsManager(@PathVariable Long departmentManagerId, @PathVariable Long operationsManagerId){
        departmentManagerService.assignDepartmentManagerToOperationsManager(departmentManagerId, operationsManagerId);
        return ResponseEntity.ok("Department Manager Assigned to Operations Manager Successfully");
    }

    // Update Operations Manager of Department Manager
    @PutMapping("/{departmentManagerId}/operations-manager/{operationsManagerId}")
    public ResponseEntity<String> updateDepartmentManagerToOperationsManager(@PathVariable Long departmentManagerId, @PathVariable Long operationsManagerId){
        departmentManagerService.updateDepartmentManagerToOperationsManager(departmentManagerId, operationsManagerId);
        return ResponseEntity.ok("Department Manager Operations Manager Updated Successfully");
    }

    // Retrieve all department managers by OperationsManagerID
    @GetMapping("/operations-manager/{operationsManagerId}")
    public List<DepartmentManagerResponseDto> getDepartmentManagersByOperationsManager(@PathVariable Long operationsManagerId){
        List<DepartmentManager> retrievedDepartmentManagers = departmentManagerService.getDepartmentManagersByOperationsManagerId(operationsManagerId);
        List<DepartmentManagerResponseDto> departmentManagerResponseDtos = new ArrayList<>();
        for(DepartmentManager departmentManager : retrievedDepartmentManagers){
            departmentManagerResponseDtos.add(modelMapper.map(departmentManager, DepartmentManagerResponseDto.class));
        }
        return departmentManagerResponseDtos;
    }


}
