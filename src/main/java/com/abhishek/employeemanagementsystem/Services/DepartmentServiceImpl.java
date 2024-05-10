package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.DepartmentIDAlreadyExistsException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidDepartmentIDException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidPasswordForUsernameException;
import com.abhishek.employeemanagementsystem.Exceptions.NoEmployeesFoundException;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private AdminService adminService;
    private DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(CreateDepartmentRequestDto createDepartmentRequestDto) {
        Department department = createDepartmentRequestDto.getDepartment();
        AdminLoginRequestDto adminLoginRequestDto = createDepartmentRequestDto.getAdminLoginRequestDto();
        // Log in the admin
        Admin admin = adminService.adminLogin(adminLoginRequestDto);
        if (department.getId() != null){
            throw new DepartmentIDAlreadyExistsException("Department id already exists");
        }
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id, AdminLoginRequestDto adminLoginRequestDto) {
        // Log in the admin
        Admin admin = adminService.adminLogin(adminLoginRequestDto);
        // Check if the department Id exists or not
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw new InvalidDepartmentIDException("Invalid Department Id", id);
        }
        departmentRepository.deleteById(id);
    }

    @Override
    public Department updateDepartment(Long id, UpdateDepartmentRequestDto updateDepartmentRequestDto) {
        DepartmentUpdateRequestDto departmentUpdateRequestDto = updateDepartmentRequestDto.getDepartmentUpdateRequestDto();
        AdminLoginRequestDto adminLoginRequestDto = updateDepartmentRequestDto.getAdminLoginRequestDto();
        // Log in the admin
        Admin admin = adminService.adminLogin(adminLoginRequestDto);
        // Check if the department Id exists or not
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw new InvalidDepartmentIDException("Invalid Department Id", id);
        }
        department.get().setName(departmentUpdateRequestDto.getName());
        department.get().setDescription(departmentUpdateRequestDto.getDescription());
        return departmentRepository.save(department.get());
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()){
            throw new InvalidDepartmentIDException("Invalid Department Id", id);
        }
        return department.get();
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long id) {
        List<Employee> allEmployees = departmentRepository.findAllEmployeesByDepartmentId(id);
        if (allEmployees.isEmpty()){
            throw new NoEmployeesFoundException("No Employees Found");
        }
        return allEmployees;
    }
}
