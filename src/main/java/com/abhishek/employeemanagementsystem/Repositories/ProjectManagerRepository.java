package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long> {
    @Override
    List<ProjectManager> findAll();

    @Override
    Optional<ProjectManager> findById(Long id);

    @Override
    ProjectManager save(ProjectManager projectManager);

    @Override
    void deleteById(Long id);
}
