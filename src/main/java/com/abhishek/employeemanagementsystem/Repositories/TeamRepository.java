package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.OperationsManager;
import com.abhishek.employeemanagementsystem.Models.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Teams, Long> {

    @Override
    Teams save(Teams team);

    @Override
    Optional<Teams> findById(Long id);

    @Override
    List<Teams> findAll();

    @Override
    void deleteById(Long aLong);
}
