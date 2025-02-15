package com.example.albartros.controller;

import com.example.albartros.dto.CountryDto;
import com.example.albartros.dto.HttpApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.albartros.service.CountryService;
@RequiredArgsConstructor
@RestController
@RequestMapping("country/")
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "Create a Country", description = "Add a new Country")
    @PostMapping
    public HttpApiResponse<CountryDto> createCountry(@RequestBody @Valid CountryDto dto){
        return this.countryService.createCountry(dto);
    }

    @Operation(summary = "Get a Country By Id", description = "Fetch a Country using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<CountryDto> getCountryById(@PathVariable("id") Long id){
        return this.countryService.getCountryById(id);
    }

    @Operation(summary = "Update a Country",
            description = "Update a Country By its ID and by giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<CountryDto> updateCountryById(@PathVariable("id") Long id,
                                                         @RequestBody CountryDto dto){
        return this.countryService.updateCountryById(id, dto);
    }

    @Operation(summary = "Delete a Country by ID", description = "Delete a Country using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<CountryDto> deleteCountryById(@PathVariable("id") Long id){
        return this.countryService.deleteCountryById(id);
    }

}
