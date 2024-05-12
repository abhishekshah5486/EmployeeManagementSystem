package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateTechnicalManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidTechnicalManagerIDException;
import com.abhishek.employeemanagementsystem.Exceptions.NoTechnicalManagersFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.TechnicalManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Models.TechnicalManager;
import com.abhishek.employeemanagementsystem.Repositories.TechnicalManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalManagerServiceImpl implements TechnicalManagerService {
    private TechnicalManagerRepository technicalManagerRepository;

    public TechnicalManagerServiceImpl(TechnicalManagerRepository technicalManagerRepository) {
        this.technicalManagerRepository = technicalManagerRepository;
    }

    @Override
    public TechnicalManager createTechnicalManager(CreateTechnicalManagerRequestDto createTechnicalManagerRequestDto) {
        TechnicalManager technicalManager = new TechnicalManager();
        technicalManager.setName(createTechnicalManagerRequestDto.getName());
        technicalManager.setEmail(createTechnicalManagerRequestDto.getEmail());
        // Validate the username first and then create Technical Manager
        technicalManager.setUsername(createTechnicalManagerRequestDto.getUsername());
        technicalManager.setPassword(createTechnicalManagerRequestDto.getPassword());
        technicalManager.setDateOfJoining(createTechnicalManagerRequestDto.getDateOfJoining());
        return technicalManagerRepository.save(technicalManager);
    }

    @Override
    public void deleteTechnicalManager(Long id) {
        Optional<TechnicalManager> technicalManager = technicalManagerRepository.findById(id);
        if (technicalManager.isEmpty()){
            throw new InvalidTechnicalManagerIDException("Invalid Technical Manager Id", id);
        }
        technicalManagerRepository.deleteById(id);
    }

    @Override
    public TechnicalManager updateTechnicalManager(Long id, UpdateTechnicalManagerRequestDto updateTechnicalManagerRequestDto) {
        Optional<TechnicalManager> technicalManager = technicalManagerRepository.findById(id);
        if (technicalManager.isEmpty()){
            throw new InvalidTechnicalManagerIDException("Invalid Technical Manager Id", id);
        }
        technicalManager.get().setEmail(updateTechnicalManagerRequestDto.getEmail());
        technicalManager.get().setName(updateTechnicalManagerRequestDto.getName());
        // Validate the username first
        technicalManager.get().setUsername(updateTechnicalManagerRequestDto.getUsername());
        return technicalManagerRepository.save(technicalManager.get());
    }

    @Override
    public TechnicalManager getTechnicalManagerById(Long id) {
        Optional<TechnicalManager> technicalManager = technicalManagerRepository.findById(id);
        if (technicalManager.isEmpty()){
            throw new TechnicalManagerIDNotFoundException("Invalid Technical Manager Id", id);
        }
        return technicalManager.get();
    }

    @Override
    public List<TechnicalManager> getAllTechnicalManagers() {
        List<TechnicalManager> technicalManagers = technicalManagerRepository.findAll();
        if (technicalManagers.isEmpty()){
            throw new NoTechnicalManagersFoundException("No Technical Manager found");
        }
        return technicalManagers;
    }

}
