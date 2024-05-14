package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.DepartmentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentManagerRepository extends JpaRepository<DepartmentManager, Long> {
    @Override
    DepartmentManager save(DepartmentManager departmentManager);

    @Override
    Optional<DepartmentManager> findById(Long id);

    @Override
    List<DepartmentManager> findAll();

    @Override
    void deleteById(Long id);
}
