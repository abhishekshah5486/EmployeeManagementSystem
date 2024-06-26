package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateOperationsManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.NoDepartmentManagersFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoOperationsManagersFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.OperationsManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Models.Executive;
import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import com.abhishek.employeemanagementsystem.Repositories.OperationsManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationsManagerServiceImpl implements OperationsManagerService{

    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private ExecutiveServiceImpl executiveService;
    private OperationsManagerRepository operationsManagerRepository;
    public OperationsManagerServiceImpl(OperationsManagerRepository operationsManagerRepository) {
        this.operationsManagerRepository = operationsManagerRepository;
    }

    @Override
    public OperationsManager createOperationsManager(CreateOperationsManagerRequestDto createOperationsManagerRequestDto) {
        OperationsManager operationsManager = new OperationsManager();
        operationsManager.setName(createOperationsManagerRequestDto.getName());
        operationsManager.setEmail(createOperationsManagerRequestDto.getEmail());
        operationsManager.setDateOfJoining(createOperationsManagerRequestDto.getDateOfJoining());
        operationsManager.setUsername(createOperationsManagerRequestDto.getUsername());

        return operationsManagerRepository.save(operationsManager);
    }

    @Override
    public OperationsManager updateOperationsManager(Long id, UpdateOperationsManagerRequestDto updateOperationsManagerRequestDto) {
        // Implement update logic
        return null;
    }

    @Override
    public void deleteOperationsManager(Long id) {
        // Fetch the operations manager by id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(id);
        if (operationsManager.isEmpty()) {
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", id);
        }
        operationsManagerRepository.deleteById(id);
    }

    @Override
    public OperationsManager getOperationsManagerById(Long id) {
        // Fetch the operations manager by id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(id);
        if (operationsManager.isEmpty()) {
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", id);
        }
        return operationsManager.get();
    }

    @Override
    public List<OperationsManager> getAllOperationsManagers() {
        List<OperationsManager> operationsManagers = operationsManagerRepository.findAll();
        if (operationsManagers.isEmpty()) {
            throw new NoOperationsManagersFoundException("No Operations Managers Found.");
        }
        return operationsManagers;
    }

    @Override
    public OperationsManager assignDepartmentToOperationsManager(Long operationsManagerId, Long departmentId) {
        // Fetch the operations manager by id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(operationsManagerId);
        if (operationsManager.isEmpty()) {
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", operationsManagerId);
        }
        // Fetch department by department id
        Department department = departmentService.getDepartmentById(departmentId);
        operationsManager.get().setDepartment(department);
        return operationsManagerRepository.save(operationsManager.get());
    }

    @Override
    public OperationsManager updateOperationsManagerDepartment(Long operationsManagerId, Long departmentId) {
        // Fetch the operations manager by id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(operationsManagerId);
        if (operationsManager.isEmpty()) {
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", operationsManagerId);
        }
        // Fetch department by department id
        Department department = departmentService.getDepartmentById(departmentId);
        operationsManager.get().setDepartment(department);
        return operationsManagerRepository.save(operationsManager.get());
    }

    @Override
    public OperationsManager assignOperationsManagerToExecutive(Long operationsManagerId, Long executiveId) {
        // Fetch Operations Manager by Operations Manager id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(operationsManagerId);
        if (operationsManager.isEmpty()){
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", operationsManagerId);
        }
        // Fetch Executive by Executive id
        Executive executive = executiveService.getExecutiveById(executiveId);

        operationsManager.get().setExecutive(executive);

        return operationsManagerRepository.save(operationsManager.get());
    }

    @Override
    public OperationsManager updateOperationsManagerToExecutive(Long operationsManagerId, Long executiveId) {
        // Fetch Operations Manager by Operations Manager id
        Optional<OperationsManager> operationsManager = operationsManagerRepository.findById(operationsManagerId);
        if (operationsManager.isEmpty()){
            throw new OperationsManagerIDNotFoundException("No operations manager found with this id !", operationsManagerId);
        }
        // Fetch Executive by Executive id
        Executive executive = executiveService.getExecutiveById(executiveId);

        operationsManager.get().setExecutive(executive);

        return operationsManagerRepository.save(operationsManager.get());
    }

    @Override
    public List<OperationsManager> getOperationsManagersByExecutiveId(Long executiveId) {
        List<OperationsManager> allOperationsManagers = operationsManagerRepository.findAll();
        if (allOperationsManagers.isEmpty()){
            throw new NoOperationsManagersFoundException("No operations managers found");
        }
        List<OperationsManager> returnOperationsManagers = new ArrayList<>();
        for (OperationsManager operationsManager : allOperationsManagers){
            if (operationsManager.getExecutive().getId().equals(executiveId)){
                returnOperationsManagers.add(operationsManager);
            }
        }
        return returnOperationsManagers;
    }


}
