package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.Admin;
import org.hibernate.sql.ast.Clause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Override
    Admin save(Admin admin);

    @Override
    Optional<Admin> findById(Long id);

    @Override
    List<Admin> findAll();

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Admin a WHERE a.username = :username")
    Boolean existsByUsername(String username);

    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    Optional<Admin> findByUsername(String username);
}
