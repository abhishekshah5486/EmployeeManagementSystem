package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateRiskManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateRiskManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidRiskManagerIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoRiskManagersFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.RiskManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Models.RiskManager;
import com.abhishek.employeemanagementsystem.Repositories.RiskManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiskManagerServiceImpl implements RiskManagerService {
    private RiskManagerRepository riskManagerRepository;

    public RiskManagerServiceImpl(RiskManagerRepository riskManagerRepository) {
        this.riskManagerRepository = riskManagerRepository;
    }

    @Override
    public RiskManager createRiskManager(CreateRiskManagerRequestDto createRiskManagerRequestDto) {
        RiskManager riskManager = new RiskManager();
        riskManager.setName(createRiskManagerRequestDto.getName());
        riskManager.setEmail(createRiskManagerRequestDto.getEmail());
        // Validate the username first and then create Risk Manager
        riskManager.setUsername(createRiskManagerRequestDto.getUsername());
        riskManager.setPassword(createRiskManagerRequestDto.getPassword());
        riskManager.setDateOfJoining(createRiskManagerRequestDto.getDateOfJoining());
        return riskManagerRepository.save(riskManager);
    }

    @Override
    public void deleteRiskManager(Long id) {
        Optional<RiskManager> riskManager = riskManagerRepository.findById(id);
        if (riskManager.isEmpty()){
            throw new InvalidRiskManagerIDException("Invalid Risk Manager Id", id);
        }
        riskManagerRepository.deleteById(id);
    }

    @Override
    public RiskManager updateRiskManager(Long id, UpdateRiskManagerRequestDto updateRiskManagerRequestDto) {
        Optional<RiskManager> riskManager = riskManagerRepository.findById(id);
        if (riskManager.isEmpty()){
            throw new InvalidRiskManagerIDException("Invalid Risk Manager Id", id);
        }
        riskManager.get().setEmail(updateRiskManagerRequestDto.getEmail());
        riskManager.get().setName(updateRiskManagerRequestDto.getName());
        // Validate the username first
        riskManager.get().setUsername(updateRiskManagerRequestDto.getUsername());
        return riskManagerRepository.save(riskManager.get());
    }

    @Override
    public RiskManager getRiskManagerById(Long id) {
        Optional<RiskManager> riskManager = riskManagerRepository.findById(id);
        if (riskManager.isEmpty()){
            throw new RiskManagerIDNotFoundException("Invalid Risk Manager Id", id);
        }
        return riskManager.get();
    }

    @Override
    public List<RiskManager> getAllRiskManagers() {
        List<RiskManager> riskManagers = riskManagerRepository.findAll();
        if (riskManagers.isEmpty()){
            throw new NoRiskManagersFoundException("No Risk Manager found");
        }
        return riskManagers;
    }

}
