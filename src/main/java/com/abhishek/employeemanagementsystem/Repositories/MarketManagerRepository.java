package com.abhishek.employeemanagementsystem.Repositories;

import com.abhishek.employeemanagementsystem.Models.MarketManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketManagerRepository extends JpaRepository<MarketManager, Long> {
    @Override
    MarketManager save(MarketManager marketManager);

    @Override
    Optional<MarketManager> findById(Long id);

    @Override
    List<MarketManager> findAll();

    @Override
    void deleteById(Long id);
}
