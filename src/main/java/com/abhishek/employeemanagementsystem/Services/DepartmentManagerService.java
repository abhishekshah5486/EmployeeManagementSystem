package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;

import java.util.List;

public interface DepartmentManagerService {
    DepartmentManager createDepartmentManager(CreateDepartmentManagerRequestDto createDepartmentManagerRequestDto);
    DepartmentManager updateDepartmentManager(UpdateDepartmentManagerRequestDto updateDepartmentManagerRequestDto);
    void deleteDepartmentManager(int id);
    DepartmentManager getDepartmentManagerById(int id);
    List<DepartmentManager> getAllDepartmentManagers();
}
