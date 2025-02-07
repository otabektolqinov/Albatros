package com.example.albartros.controller;

import com.example.albartros.dto.FoodDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("food")
@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public HttpApiResponse<FoodDto> createFood(@RequestBody @Valid FoodDto dto){
        return this.foodService.createFood(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<FoodDto> getFoodById(@PathVariable("id") Long id){
        return this.foodService.getFoodById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<FoodDto> updateFoodById(@PathVariable("id") Long id,
                                                   @RequestBody FoodDto dto){
        return this.foodService.updateFoodById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<FoodDto> deleteFoodById(@PathVariable("id") Long id){
        return this.foodService.deleteFoodById(id);
    }

    @GetMapping("/get-all-by-country/{id}")
    public HttpApiResponse<List<FoodDto>> getAllFood(@PathVariable("id") Long countryId){
        return this.foodService.getAllFoodByCountry(countryId);
    }
}
