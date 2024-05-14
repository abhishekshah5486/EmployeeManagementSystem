package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationsManagerRepository extends JpaRepository<OperationsManager, Long> {
    @Override
    OperationsManager save(OperationsManager operationsManager);

    @Override
    Optional<OperationsManager> findById(Long id);

    @Override
    List<OperationsManager> findAll();

    @Override
    void deleteById(Long id);
}
