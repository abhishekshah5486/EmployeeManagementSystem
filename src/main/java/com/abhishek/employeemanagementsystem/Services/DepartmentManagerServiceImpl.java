package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.DepartmentManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoDepartmentManagersFoundException;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentManagerServiceImpl implements DepartmentManagerService {

    @Autowired
    private DepartmentServiceImpl departmentService;
    private DepartmentManagerRepository departmentManagerRepository;
    public DepartmentManagerServiceImpl(DepartmentManagerRepository departmentManagerRepository) {
        this.departmentManagerRepository = departmentManagerRepository;
    }

    @Override
    public DepartmentManager createDepartmentManager(CreateDepartmentManagerRequestDto createDepartmentManagerRequestDto) {
        DepartmentManager departmentManager = new DepartmentManager();
        departmentManager.setName(createDepartmentManagerRequestDto.getName());
        departmentManager.setEmail(createDepartmentManagerRequestDto.getEmail());
        departmentManager.setDateOfJoining(createDepartmentManagerRequestDto.getDateOfJoining());
        departmentManager.setUsername(createDepartmentManagerRequestDto.getUsername());

        return departmentManagerRepository.save(departmentManager);
    }

    @Override
    public DepartmentManager updateDepartmentManager(Long id, UpdateDepartmentManagerRequestDto updateDepartmentManagerRequestDto) {
        return null;
    }

    @Override
    public void deleteDepartmentManager(Long id) {
        // Fetch the department manager by id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(id);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", id);
        }
        departmentManagerRepository.deleteById(id);
    }

    @Override
    public DepartmentManager getDepartmentManagerById(Long id) {
        // Fetch the department manager by id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(id);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", id);
        }
        return departmentManager.get();
    }

    @Override
    public List<DepartmentManager> getAllDepartmentManagers() {
        List<DepartmentManager> departmentManagers = departmentManagerRepository.findAll();
        if (departmentManagers.isEmpty()){
            throw new NoDepartmentManagersFoundException("No Department Managers Found.");
        }
        return departmentManagers;
    }

    @Override
    public DepartmentManager assignDepartmentToDepartmentManager(Long departmentManagerId, Long departmentId) {
        // Fetch the department manager by id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        // fetch department by department id
        Department department = departmentService.getDepartmentById(departmentId);
        departmentManager.get().setDepartment(department);
        return departmentManagerRepository.save(departmentManager.get());
    }

    @Override
    public DepartmentManager updateDepartmentManagerDepartment(Long departmentManagerId, Long departmentId) {
        // Fetch the department manager by id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        // fetch department by department id
        Department department = departmentService.getDepartmentById(departmentId);
        departmentManager.get().setDepartment(department);
        return departmentManagerRepository.save(departmentManager.get());
    }
}
