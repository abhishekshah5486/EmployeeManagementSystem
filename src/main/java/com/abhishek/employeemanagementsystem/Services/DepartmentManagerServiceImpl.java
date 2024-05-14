package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentManagerServiceImpl implements DepartmentManagerService {

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
    public DepartmentManager updateDepartmentManager(UpdateDepartmentManagerRequestDto updateDepartmentManagerRequestDto) {
        return null;
    }

    @Override
    public void deleteDepartmentManager(int id) {

    }

    @Override
    public DepartmentManager getDepartmentManagerById(int id) {
        return null;
    }

    @Override
    public List<DepartmentManager> getAllDepartmentManagers() {
        return List.of();
    }
}
