package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Long> {
    @Override
    Executive save(Executive executive);

    @Override
    Optional<Executive> findById(Long id);

    @Override
    List<Executive> findAll();

    @Override
    void deleteById(Long id);
}
