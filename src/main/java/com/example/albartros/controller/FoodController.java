package com.example.albartros.controller;

import com.example.albartros.dto.FoodDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("food/")
@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @Operation(summary = "Create new Food", description = "Add new Food")
    @PostMapping
    public HttpApiResponse<FoodDto> createFood(@RequestBody @Valid FoodDto dto){
        return this.foodService.createFood(dto);
    }

    @Operation(summary = "Get Food by ID", description = "Fetch Food using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<FoodDto> getFoodById(@PathVariable("id") Long id){
        return this.foodService.getFoodById(id);
    }

    @Operation(summary = "Update Food By ID",
            description = "Update Food by using its ID and giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<FoodDto> updateFoodById(@PathVariable("id") Long id,
                                                   @RequestBody FoodDto dto){
        return this.foodService.updateFoodById(id, dto);
    }

    @Operation(summary = "Delete Food by ID", description = "Delete food using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<FoodDto> deleteFoodById(@PathVariable("id") Long id){
        return this.foodService.deleteFoodById(id);
    }

    @Operation(summary = "Get all Food by Country",
            description = "Fetch all Food in a specific country by giving a Country ID")
    @GetMapping("/get-all-by-country/{id}")
    public HttpApiResponse<List<FoodDto>> getAllFood(@PathVariable("id") Long countryId){
        return this.foodService.getAllFoodByCountry(countryId);
    }
}
