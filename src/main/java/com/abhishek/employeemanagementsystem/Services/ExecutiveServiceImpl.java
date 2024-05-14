package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateExecutiveRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.ExecutiveIDNotFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoExecutivesFoundException;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Executive;
import com.abhishek.employeemanagementsystem.Repositories.ExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutiveServiceImpl implements ExecutiveService {

    @Autowired
    private DepartmentServiceImpl departmentService;
    private ExecutiveRepository executiveRepository;
    public ExecutiveServiceImpl(ExecutiveRepository executiveRepository) {
        this.executiveRepository = executiveRepository;
    }

    @Override
    public Executive createExecutive(CreateExecutiveRequestDto createExecutiveRequestDto) {
        Executive executive = new Executive();
        executive.setName(createExecutiveRequestDto.getName());
        executive.setEmail(createExecutiveRequestDto.getEmail());
        executive.setDateOfJoining(createExecutiveRequestDto.getDateOfJoining());
        executive.setUsername(createExecutiveRequestDto.getUsername());

        return executiveRepository.save(executive);
    }

    @Override
    public Executive updateExecutive(Long id, UpdateExecutiveRequestDto updateExecutiveRequestDto) {
        // Implement update logic
        return null;
    }

    @Override
    public void deleteExecutive(Long id) {
        // Fetch the executive by id
        Optional<Executive> executive = executiveRepository.findById(id);
        if (executive.isEmpty()) {
            throw new ExecutiveIDNotFoundException("No executive found with this id !", id);
        }
        executiveRepository.deleteById(id);
    }

    @Override
    public Executive getExecutiveById(Long id) {
        // Fetch the executive by id
        Optional<Executive> executive = executiveRepository.findById(id);
        if (executive.isEmpty()) {
            throw new ExecutiveIDNotFoundException("No executive found with this id !", id);
        }
        return executive.get();
    }

    @Override
    public List<Executive> getAllExecutives() {
        List<Executive> executives = executiveRepository.findAll();
        if (executives.isEmpty()) {
            throw new NoExecutivesFoundException("No Executives Found.");
        }
        return executives;
    }

//    @Override
//    public Executive assignDepartmentToExecutive(Long executiveId, Long departmentId) {
//        // Fetch the executive by id
//        Optional<Executive> executive = executiveRepository.findById(executiveId);
//        if (executive.isEmpty()) {
//            throw new ExecutiveIDNotFoundException("No executive found with this id !", executiveId);
//        }
//        // Fetch department by department id
//        Department department = departmentService.getDepartmentById(departmentId);
//        executive.get().setDepartment(department);
//        return executiveRepository.save(executive.get());
//    }
//
//    @Override
//    public Executive updateExecutiveDepartment(Long executiveId, Long departmentId) {
//        // Fetch the executive by id
//        Optional<Executive> executive = executiveRepository.findById(executiveId);
//        if (executive.isEmpty()) {
//            throw new ExecutiveIDNotFoundException("No executive found with this id !", executiveId);
//        }
//        // Fetch department by department id
//        Department department = departmentService.getDepartmentById(departmentId);
//        executive.get().setDepartment(department);
//        return executiveRepository.save(executive.get());
//    }


}
