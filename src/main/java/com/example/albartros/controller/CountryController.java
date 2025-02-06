package com.example.albartros.controller;

import com.example.albartros.dto.CountryDto;
import com.example.albartros.dto.HttpApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.albartros.service.CountryService;
@RequiredArgsConstructor
@RestController
@RequestMapping("country")
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public HttpApiResponse<CountryDto> createCountry(@RequestBody CountryDto dto){
        return this.countryService.createCountry(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<CountryDto> getCountryById(@PathVariable("id") Long id){
        return this.countryService.getCountryById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<CountryDto> updateCountryById(@PathVariable("id") Long id,
                                                         @RequestBody CountryDto dto){
        return this.countryService.updateCountryById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<CountryDto> deleteCountryById(@PathVariable("id") Long id){
        return this.countryService.deleteCountryById(id);
    }

}
