package com.example.albartros.service;

import com.example.albartros.dto.AbbreviationDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbbreviationService {

    HttpApiResponse<AbbreviationDto> createAbbreviation(AbbreviationDto dto);
    HttpApiResponse<AbbreviationDto> getAbbreviationById(Long id);
    HttpApiResponse<AbbreviationDto> updateAbbreviationById(Long id, AbbreviationDto dto);
    HttpApiResponse<AbbreviationDto> deleteAbbreviationById(Long id);
    HttpApiResponse<List<AbbreviationDto>> getAllAbbreviationsByCategory(Long categoryId);
    HttpApiResponse<List<AbbreviationDto>> getAllAbbreviations();

}
