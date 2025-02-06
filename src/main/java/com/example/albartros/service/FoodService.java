package com.example.albartros.service;

import com.example.albartros.dto.FoodDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {

    HttpApiResponse<FoodDto> createFood(FoodDto dto);
    HttpApiResponse<FoodDto> getFoodById(Long id);
    HttpApiResponse<FoodDto> updateFoodById(Long id, FoodDto dto);
    HttpApiResponse<FoodDto> deleteFoodById(Long id);
    HttpApiResponse<List<FoodDto>> getAllFoodByCountry(Long countryId);
}
