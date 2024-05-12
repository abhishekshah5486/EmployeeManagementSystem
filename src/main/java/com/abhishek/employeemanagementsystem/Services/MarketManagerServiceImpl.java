package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Exceptions.InvalidMarketManagerIDException;
import com.abhishek.employeemanagementsystem.Exceptions.MarketManagerIDNotFoundException;
import com.abhishek.employeemanagementsystem.Exceptions.NoMarketManagersFoundException;
import com.abhishek.employeemanagementsystem.Models.MarketManager;
import com.abhishek.employeemanagementsystem.Repositories.MarketManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketManagerServiceImpl implements MarketManagerService {
    private MarketManagerRepository marketManagerRepository;

    public MarketManagerServiceImpl(MarketManagerRepository marketManagerRepository) {
        this.marketManagerRepository = marketManagerRepository;
    }

    @Override
    public MarketManager createMarketManager(CreateMarketManagerRequestDto createMarketManagerRequestDto) {
        MarketManager marketManager = new MarketManager();
        marketManager.setName(createMarketManagerRequestDto.getName());
        marketManager.setEmail(createMarketManagerRequestDto.getEmail());
        // Validate the username first and then create Market Manager
        marketManager.setUsername(createMarketManagerRequestDto.getUsername());
        marketManager.setPassword(createMarketManagerRequestDto.getPassword());
        marketManager.setDateOfJoining(createMarketManagerRequestDto.getDateOfJoining());
        return marketManagerRepository.save(marketManager);
    }

    @Override
    public void deleteMarketManager(Long id) {
        Optional<MarketManager> marketManager = marketManagerRepository.findById(id);
        if (marketManager.isEmpty()){
            throw new InvalidMarketManagerIDException("Invalid Market Manager Id", id);
        }
        marketManagerRepository.deleteById(id);
    }

    @Override
    public MarketManager updateMarketManager(Long id, UpdateMarketManagerRequestDto updateMarketManagerRequestDto) {
        Optional<MarketManager> marketManager = marketManagerRepository.findById(id);
        if (marketManager.isEmpty()){
            throw new InvalidMarketManagerIDException("Invalid Market Manager Id", id);
        }
        marketManager.get().setEmail(updateMarketManagerRequestDto.getEmail());
        marketManager.get().setName(updateMarketManagerRequestDto.getName());
        // Validate the username first
        marketManager.get().setUsername(updateMarketManagerRequestDto.getUsername());
        return marketManagerRepository.save(marketManager.get());
    }

    @Override
    public MarketManager getMarketManagerById(Long id) {
        Optional<MarketManager> marketManager = marketManagerRepository.findById(id);
        if (marketManager.isEmpty()){
            throw new MarketManagerIDNotFoundException("Invalid Market Manager Id", id);
        }
        return marketManager.get();
    }

    @Override
    public List<MarketManager> getAllMarketManagers() {
        List<MarketManager> marketManagers = marketManagerRepository.findAll();
        if (marketManagers.isEmpty()){
            throw new NoMarketManagersFoundException("No Market Manager found");
        }
        return marketManagers;
    }

}
