package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.AdminLoginRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.RoleRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.NoEmployeesFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoEmployeesFoundForRoleException;
import com.abhishek.employeemanagementsystem.Exceptions.RoleNotFoundException;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.Employee;
import com.abhishek.employeemanagementsystem.Models.LoginStatus;
import com.abhishek.employeemanagementsystem.Models.Role;
import com.abhishek.employeemanagementsystem.Repositories.EmployeeRepository;
import com.abhishek.employeemanagementsystem.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;
    private AdminService adminService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    public RoleServiceImpl(RoleRepository roleRepository, AdminService adminService) {
        this.roleRepository = roleRepository;
        this.adminService = adminService;
    }

    @Override
    public Role createRole(RoleRequestDto roleRequestDto) {
        Role role = roleRequestDto.getRole();
        AdminLoginRequestDto adminLoginRequestDto = roleRequestDto.getAdminLoginRequestDto();

        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            adminService.adminLogin(adminLoginRequestDto);
        }
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, RoleRequestDto roleRequestDto) {
        AdminLoginRequestDto adminLoginRequestDto = roleRequestDto.getAdminLoginRequestDto();

        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            adminService.adminLogin(adminLoginRequestDto);
        }
        Role role = getRoleById(id);
        role.setName(roleRequestDto.getRole().getName());
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isEmpty()){
            throw new RoleNotFoundException("Invalid ID Passsed");
        }
        return role.get();
    }

    @Override
    public void deleteRole(Long id, AdminLoginRequestDto adminLoginRequestDto) {
        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            adminService.adminLogin(adminLoginRequestDto);
        }
        roleRepository.deleteById(id);
    }

    @Override
    public Employee assignRoleToEmployee(Long employeeId, Long roleId, AdminLoginRequestDto adminLoginRequestDto) {
        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            adminService.adminLogin(adminLoginRequestDto);
        }
        Role role = getRoleById(roleId);
        return employeeService.assignRoleToEmployee(employeeId, role);
    }

    @Override
    public Employee changeRoleToEmployee(Long employeeId, Long roleId, AdminLoginRequestDto adminLoginRequestDto) {
        // Fetch the admin by username
        Admin admin = adminService.getAdminByUsername(adminLoginRequestDto.getUsername());
        // Check the login status of the current admin
        // if already logged in, return the created role, else log in the admin first
        if (admin.getLoginStatus().equals(LoginStatus.LOGGEDOUT)){
            adminService.adminLogin(adminLoginRequestDto);
        }
        Role role = getRoleById(roleId);
        return employeeService.assignRoleToEmployee(employeeId, role);
    }

    @Override
    public List<Employee> getAllEmployeesByRoleId(Long roleId) {
        Role role = getRoleById(roleId);
        List<Long> employeeIds = role.getEmployeeIds();
        List<Employee> employees = new ArrayList<>();
        for (Long employeeId : employeeIds){
            Employee employee = employeeService.getEmployeeById(employeeId);
            employees.add(employee);
        }
        if (employees.isEmpty()){
            throw new NoEmployeesFoundForRoleException("No employees found for the role Id!", role.getId(), role.getName());
        }
        return employees;
    }
}
