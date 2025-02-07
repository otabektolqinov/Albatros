package com.example.albartros.service.impl;

import com.example.albartros.dto.CountryDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.repository.CountryRepository;
import com.example.albartros.service.CountryService;
import com.example.albartros.service.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public HttpApiResponse<CountryDto> createCountry(CountryDto dto) {
        try {
            Country entity = countryMapper.toEntity(dto);
            Country saved = countryRepository.save(entity);
            return HttpApiResponse.<CountryDto>builder()
                    .content(countryMapper.toDto(saved))
                    .status(HttpStatus.CREATED)
                    .message("ok")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CountryDto> getCountryById(Long id) {
        try {
            Optional<Country> optional = countryRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Country with %d id is not found", id));
            }
            CountryDto dto = countryMapper.toDto(optional.get());
            return HttpApiResponse.<CountryDto>builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .content(dto)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CountryDto> updateCountryById(Long id, CountryDto dto) {
        try {
            Optional<Country> optional = countryRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Country with %d id is not found", id));
            }
            Country country = countryMapper.updateAllFields(optional.get(), dto);
            Country saved = countryRepository.save(country);
            return HttpApiResponse.<CountryDto>builder()
                    .content(countryMapper.toDto(saved))
                    .status(HttpStatus.OK)
                    .message("Successfully updated")
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CountryDto> deleteCountryById(Long id) {
        try {
            Optional<Country> country = countryRepository.findByIdAndDeletedAtIsNull(id);
            if (country.isEmpty()){
                throw new ContentNotFoundException(String.format("Country with %d id is not found", id));
            }
            Country country1 = country.get();
            country1.setDeletedAt(LocalDateTime.now());
            countryRepository.save(country1);
            return HttpApiResponse.<CountryDto>builder()
                    .message("Successfully deleted")
                    .status(HttpStatus.OK)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


}
