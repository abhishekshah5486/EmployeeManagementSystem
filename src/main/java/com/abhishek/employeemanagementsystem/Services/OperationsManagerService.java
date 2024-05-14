package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.OperationsManager;

import java.util.List;

public interface OperationsManagerService {
    public OperationsManager createOperationsManager(CreateOperationsManagerRequestDto createOperationsManagerRequestDto);
    public OperationsManager updateOperationsManager(Long id, UpdateOperationsManagerRequestDto updateOperationsManagerRequestDto);
    public void deleteOperationsManager(Long id);
    public OperationsManager getOperationsManagerById(Long id);
    public List<OperationsManager> getAllOperationsManagers();
    OperationsManager assignDepartmentToOperationsManager(Long operationsManagerId, Long departmentId);
    OperationsManager updateOperationsManagerDepartment(Long operationsManagerId, Long departmentId);
    OperationsManager assignOperationsManagerToExecutive(Long operationsManagerId, Long executiveId);
    OperationsManager updateOperationsManagerToExecutive(Long operationsManagerId, Long executiveId);
    List<OperationsManager> getOperationsManagersByExecutiveId(Long departmentId);

}
