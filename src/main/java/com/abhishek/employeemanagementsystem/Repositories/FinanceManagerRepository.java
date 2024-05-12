package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.FinanceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinanceManagerRepository extends JpaRepository<FinanceManager, Long> {
    @Override
    FinanceManager save(FinanceManager financeManager);

    @Override
    List<FinanceManager> findAll();

    @Override
    Optional<FinanceManager> findById(Long id);

    @Override
    void deleteById(Long id);
}
