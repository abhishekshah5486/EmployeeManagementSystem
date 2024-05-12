package com.abhishek.employeemanagementsystem.Controllers;

import com.abhishek.employeemanagementsystem.Dtos.CreateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Dtos.MarketManagerResponseDto;
import com.abhishek.employeemanagementsystem.Dtos.UpdateMarketManagerRequestDto;
import com.abhishek.employeemanagementsystem.Models.MarketManager;
import com.abhishek.employeemanagementsystem.Services.MarketManagerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/market-managers")
public class MarketManagerController {
    @Autowired
    private ModelMapper modelMapper;
    private MarketManagerServiceImpl marketManagerService;

    public MarketManagerController(MarketManagerServiceImpl marketManagerService) {
        this.marketManagerService = marketManagerService;
    }

    // Create a Market Manager
    @PostMapping("/")
    public MarketManagerResponseDto createMarketManager(@RequestBody CreateMarketManagerRequestDto createMarketManagerRequestDto) {
        MarketManager marketManager = marketManagerService.createMarketManager(createMarketManagerRequestDto);
        MarketManagerResponseDto marketManagerResponseDto = modelMapper.map(marketManager, MarketManagerResponseDto.class);
        marketManagerResponseDto.setMessage("Market manager created successfully");
        return marketManagerResponseDto;
    }

    // Delete a Market Manager
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMarketManager(@PathVariable Long id) {
        marketManagerService.deleteMarketManager(id);
        return ResponseEntity.ok("Market manager deleted successfully");
    }

    // Update a Market Manager
    @PutMapping("/{id}")
    public MarketManagerResponseDto updateMarketManager(@PathVariable Long id, @RequestBody UpdateMarketManagerRequestDto updateMarketManagerRequestDto) {
        MarketManager marketManager = marketManagerService.updateMarketManager(id, updateMarketManagerRequestDto);
        MarketManagerResponseDto marketManagerResponseDto = modelMapper.map(marketManager, MarketManagerResponseDto.class);
        marketManagerResponseDto.setMessage("Market manager updated successfully");
        return marketManagerResponseDto;
    }

    // Retrieve Market Manager by Id
    @GetMapping("/{id}")
    public MarketManagerResponseDto getMarketManagerById(@PathVariable Long id) {
        MarketManager marketManager = marketManagerService.getMarketManagerById(id);
        MarketManagerResponseDto marketManagerResponseDto = modelMapper.map(marketManager, MarketManagerResponseDto.class);
        marketManagerResponseDto.setMessage("Market manager retrieved successfully");
        return marketManagerResponseDto;
    }

    // Retrieve All Market Managers
    @GetMapping("/")
    public List<MarketManagerResponseDto> getAllMarketManagers() {
        List<MarketManager> marketManagers = marketManagerService.getAllMarketManagers();
        List<MarketManagerResponseDto> marketManagerResponseDtos = new ArrayList<>();
        for (MarketManager marketManager : marketManagers) {
            MarketManagerResponseDto marketManagerResponseDto = modelMapper.map(marketManager, MarketManagerResponseDto.class);
            marketManagerResponseDto.setMessage("Market manager retrieved successfully");
        }
        return marketManagerResponseDtos;
    }

}
