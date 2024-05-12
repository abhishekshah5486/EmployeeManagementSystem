package com.abhishek.employeemanagementsystem.Services;

import com.abhishek.employeemanagementsystem.Dtos.CreateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.MarketManager;

import java.util.List;

public interface MarketManagerService {
    MarketManager createMarketManager(CreateMarketManagerRequestDto createMarketManagerRequestDto);
    void deleteMarketManager(Long id);
    MarketManager updateMarketManager(Long id, UpdateMarketManagerRequestDto updateMarketManagerRequestDto);
    MarketManager getMarketManagerById(Long id);
    List<MarketManager> getAllMarketManagers();

}
