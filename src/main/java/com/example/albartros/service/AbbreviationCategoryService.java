package com.example.albartros.service;

import com.example.albartros.dto.AbbreviationCategoryDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface AbbreviationCategoryService {

    HttpApiResponse<AbbreviationCategoryDto> createAbbreviationCategory(AbbreviationCategoryDto dto);
    HttpApiResponse<AbbreviationCategoryDto> getAbbrCategoryById(Long id);
    HttpApiResponse<AbbreviationCategoryDto> updateAbbrCategoryById(Long id, AbbreviationCategoryDto dto);
    HttpApiResponse<AbbreviationCategoryDto> deleteAbbrCategoryById(Long id);

}
