package com.example.albartros.service;

import com.example.albartros.dto.CountryDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {

    HttpApiResponse<CountryDto> createCountry(CountryDto dto);
    HttpApiResponse<CountryDto> getCountryById(Long id);
    HttpApiResponse<CountryDto> updateCountryById(Long id, CountryDto dto);
    HttpApiResponse<CountryDto> deleteCountryById(Long id);

}
