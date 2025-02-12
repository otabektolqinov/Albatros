package com.example.albartros.service;

import com.example.albartros.dto.CoverageDetailsDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoverageDetailsService{

    HttpApiResponse<CoverageDetailsDto> createCoverageDetails(CoverageDetailsDto dto);
    HttpApiResponse<CoverageDetailsDto> getCoverageDetailsById(Long id);
    HttpApiResponse<CoverageDetailsDto> updateCoverageDetailsById(Long id, CoverageDetailsDto dto);
    HttpApiResponse<CoverageDetailsDto> deleteCoverageDetailsById(Long id);

}
