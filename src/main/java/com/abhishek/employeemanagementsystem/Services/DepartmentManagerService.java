package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;

import java.util.List;

public interface DepartmentManagerService {
    DepartmentManager createDepartmentManager(CreateDepartmentManagerRequestDto createDepartmentManagerRequestDto);
    DepartmentManager updateDepartmentManager(Long id, UpdateDepartmentManagerRequestDto updateDepartmentManagerRequestDto);
    void deleteDepartmentManager(Long id);
    DepartmentManager getDepartmentManagerById(Long id);
    List<DepartmentManager> getAllDepartmentManagers();
    DepartmentManager assignDepartmentToDepartmentManager(Long departmentManagerId, Long departmentId);
    DepartmentManager updateDepartmentManagerDepartment(Long departmentManagerId, Long departmentId);
}
