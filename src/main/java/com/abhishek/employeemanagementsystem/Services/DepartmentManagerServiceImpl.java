package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.DepartmentManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateDepartmentManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.DepartmentManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoAdminsFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoDepartmentManagersFoundException;
import com.abhishek.employeemanagementsystem.Models.Admin;
import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import com.abhishek.employeemanagementsystem.Repositories.DepartmentManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentManagerServiceImpl implements DepartmentManagerService {

    @Autowired
    private AdminServiceImpl adminServiceImpl;
    @Autowired
    private DepartmentServiceImpl departmentService;
    @Autowired
    private OperationsManagerService operationsManagerService;
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

    @Override
    public DepartmentManager assignDepartmentManagerToOperationsManager(Long departmentManagerId, Long operationsManagerId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        // Fetch Operations Manager by Operations Manager id
        OperationsManager operationsManager = operationsManagerService.getOperationsManagerById(operationsManagerId);

        departmentManager.get().setOperationsManager(operationsManager);

        return departmentManagerRepository.save(departmentManager.get());
    }

    @Override
    public DepartmentManager updateDepartmentManagerToOperationsManager(Long departmentManagerId, Long operationsManagerId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        // Fetch Operations Manager by Operations Manager id
        OperationsManager operationsManager = operationsManagerService.getOperationsManagerById(operationsManagerId);

        departmentManager.get().setOperationsManager(operationsManager);

        return departmentManagerRepository.save(departmentManager.get());
    }

    @Override
    public List<DepartmentManager> getDepartmentManagersByOperationsManagerId(Long operationsManagerId) {
        List<DepartmentManager> allDepartmentManagers = departmentManagerRepository.findAll();
        if (allDepartmentManagers.isEmpty()){
            throw new NoDepartmentManagersFoundException("No department managers found");
        }
        List<DepartmentManager> returnDepartmentManagers = new ArrayList<>();
        for (DepartmentManager departmentManager : allDepartmentManagers){
            if (departmentManager.getOperationsManager().getId().equals(operationsManagerId)){
                returnDepartmentManagers.add(departmentManager);
            }
        }
        return returnDepartmentManagers;
    }

    @Override
    public DepartmentManager assignAdminToDepartmentManager(Long departmentManagerId, Long adminId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        // Fetch Admin from admin id
        Admin admin = adminServiceImpl.getAdminById(adminId);
        departmentManager.get().getAdmins().add(admin);
        return departmentManagerRepository.save(departmentManager.get());
    }

    @Override
    public void deleteAdminFromDepartmentManager(Long departmentManagerId, Long adminId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        List<Admin> admins = departmentManager.get().getAdmins();
        if (admins.isEmpty()){
            throw new NoAdminsFoundException("No admins found");
        }
        for (int j=0; j<admins.size(); j++){
            if (admins.get(j).getId().equals(adminId)){
                admins.remove(j);
            }
        }
        departmentManager.get().setAdmins(admins);
        departmentManagerRepository.save(departmentManager.get());
    }

    @Override
    public List<Admin> getAllAdminsByDepartmentManagerId(Long departmentManagerId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        List<Admin> admins = departmentManager.get().getAdmins();
        if (admins.isEmpty()){
            throw new NoAdminsFoundException("No admins found");
        }
        return admins;
    }

    @Override
    public DepartmentManager getDepartmentManagerByAdminId(Long adminId) {
        List<DepartmentManager> allDepartmentManagers = departmentManagerRepository.findAll();
        if (allDepartmentManagers.isEmpty()){
            throw new NoDepartmentManagersFoundException("No department managers found");
        }
        DepartmentManager returnDepartmentManager = null;
        for (int j=0; j<allDepartmentManagers.size(); j++){
            DepartmentManager departmentManager = allDepartmentManagers.get(j);
            List<Admin> admins = departmentManager.getAdmins();
            for (int k=0; k<admins.size(); k++){
                if (admins.get(k).getId().equals(adminId)){
                    returnDepartmentManager = departmentManager;
                    break;
                }
            }
        }
        return returnDepartmentManager;
    }

    @Override
    public Department getDepartmentManagerDepartmentById(Long departmentManagerId) {
        // Fetch Department Manager by Department Manager id
        Optional<DepartmentManager> departmentManager = departmentManagerRepository.findById(departmentManagerId);
        if (departmentManager.isEmpty()){
            throw new DepartmentManagerIDNotFoundException("No department manager found with this id !", departmentManagerId);
        }
        return departmentManager.get().getDepartment();
    }


}
