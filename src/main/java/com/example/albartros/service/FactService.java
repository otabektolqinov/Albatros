package com.example.albartros.service;

import com.example.albartros.dto.FactDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FactService {
    HttpApiResponse<FactDto> createFact(FactDto dto);

    HttpApiResponse<FactDto> getFactById(Long id);

    HttpApiResponse<List<FactDto>> getAllFacts();

    HttpApiResponse<FactDto> updateFact(Long id, FactDto dto);

    HttpApiResponse<String> deleteFactById(Long id);
}
