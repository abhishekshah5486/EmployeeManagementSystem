package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    Optional<Project> findById(Long id);

    @Override
    List<Project> findAll();

    @Override
    void deleteById(Long id);

    @Override
    Project save(Project project);

    @Query("SELECT p FROM Project p WHERE p.startDate >= :startDate AND p.endDate <= :endDate")
    List<Project> findProjectsByDateRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT p FROM Project p WHERE p.startDate = :startDate")
    List<Project> findProjectsByStartDate(LocalDate startDate);

    @Query("SELECT p FROM Project p WHERE p.endDate = :endDate")
    List<Project> findProjectsByEndDate(LocalDate endDate);
}
