package com.example.albartros.service;

import com.example.albartros.dto.AgencyDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgencyService {
    HttpApiResponse<AgencyDto> createAgency(AgencyDto dto);

    HttpApiResponse<AgencyDto> getAgencyById(Long id);

    HttpApiResponse<List<AgencyDto>> getAllAgency();

    HttpApiResponse<AgencyDto> updateTourById(Long id, AgencyDto dto);

    HttpApiResponse<String> deleteTourById(Long id);
}
