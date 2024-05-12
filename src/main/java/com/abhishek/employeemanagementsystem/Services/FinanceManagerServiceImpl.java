package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateFinanceManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.FinanceManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidFinanceManagerIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoFinanceManagersFoundException;
import com.abhishek.employeemanagementsystem.Models.FinanceManager;
import com.abhishek.employeemanagementsystem.Repositories.FinanceManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceManagerServiceImpl implements FinanceManagerService {
    private FinanceManagerRepository financeManagerRepository;

    public FinanceManagerServiceImpl(FinanceManagerRepository financeManagerRepository) {
        this.financeManagerRepository = financeManagerRepository;
    }

    @Override
    public FinanceManager createFinanceManager(CreateFinanceManagerRequestDto createFinanceManagerRequestDto) {
        FinanceManager financeManager = new FinanceManager();
        financeManager.setName(createFinanceManagerRequestDto.getName());
        financeManager.setEmail(createFinanceManagerRequestDto.getEmail());
        // Validate the username first and then create Finance Manager
        financeManager.setUsername(createFinanceManagerRequestDto.getUsername());
        financeManager.setPassword(createFinanceManagerRequestDto.getPassword());
        financeManager.setDateOfJoining(createFinanceManagerRequestDto.getDateOfJoining());
        return financeManagerRepository.save(financeManager);
    }

    @Override
    public void deleteFinanceManager(Long id) {
        Optional<FinanceManager> financeManager = financeManagerRepository.findById(id);
        if (financeManager.isEmpty()){
            throw new InvalidFinanceManagerIDException("Invalid Finance Manager Id", id);
        }
        financeManagerRepository.deleteById(id);
    }

    @Override
    public FinanceManager updateFinanceManager(Long id, UpdateFinanceManagerRequestDto updateFinanceManagerRequestDto) {
        Optional<FinanceManager> financeManager = financeManagerRepository.findById(id);
        if (financeManager.isEmpty()){
            throw new InvalidFinanceManagerIDException("Invalid Finance Manager Id", id);
        }
        financeManager.get().setEmail(updateFinanceManagerRequestDto.getEmail());
        financeManager.get().setName(updateFinanceManagerRequestDto.getName());
        // Validate the username first
        financeManager.get().setUsername(updateFinanceManagerRequestDto.getUsername());
        return financeManagerRepository.save(financeManager.get());
    }

    @Override
    public FinanceManager getFinanceManagerById(Long id) {
        Optional<FinanceManager> financeManager = financeManagerRepository.findById(id);
        if (financeManager.isEmpty()){
            throw new FinanceManagerIDNotFoundException("Invalid Finance Manager Id", id);
        }
        return financeManager.get();
    }

    @Override
    public List<FinanceManager> getAllFinanceManagers() {
        List<FinanceManager> financeManagers = financeManagerRepository.findAll();
        if (financeManagers.isEmpty()){
            throw new NoFinanceManagersFoundException("No Finance Manager found");
        }
        return financeManagers;
    }

}
