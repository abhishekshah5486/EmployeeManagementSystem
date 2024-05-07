package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.EmployeeRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.EmployeeUpdateRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.EmployeeIDAlreadyExistsException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidEmployeeIdFoundException;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.Role;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentRepository;
import com.abhishek.employeemanagementsystem.Repositories.EmployeeRepository;
import com.abhishek.employeemanagementsystem.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private RoleRepository roleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            throw new InvalidEmployeeIdFoundException("Employee id not found");
        }
        return employee.get();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        Department department = employee.getDepartment();
        if (department.getId() == null){
            Department savedDepartment = departmentRepository.save(department);
            employee.setDepartment(savedDepartment);
        }
        Role role = employee.getRole();
        if (role.getId() == null){
            Role savedRole = roleRepository.save(role);
            employee.setRole(savedRole);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeUpdateRequestDto employeeUpdateRequestDto) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()){
            throw new InvalidEmployeeIdFoundException("Employee id not found");
        }
        employee.get().setName(employeeUpdateRequestDto.getName());
        employee.get().setEmail(employeeUpdateRequestDto.getEmail());
        employee.get().setDateOfJoining(employeeUpdateRequestDto.getDateOfJoining());
        Employee updatedEmployee = employeeRepository.save(employee.get());
        return updatedEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
