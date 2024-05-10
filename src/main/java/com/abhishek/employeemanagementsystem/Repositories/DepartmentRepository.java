package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.Department;
import com.abhishek.employeemanagementsystem.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Override
    Department save(Department department);

    @Override
    void deleteById(Long id);

    @Override
    Optional<Department> findById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.department.id = :id")
    List<Employee> findAllEmployeesByDepartmentId(Long id);
}
