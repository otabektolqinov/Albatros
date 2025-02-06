package com.example.albartros.service;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DestinationService {
    HttpApiResponse<DestinationDto> createDestination(DestinationDto dto);

    HttpApiResponse<DestinationDto> getDestinationById(Long id);

    HttpApiResponse<List<DestinationDto>> getAllDestinations();

    HttpApiResponse<DestinationDto> updateDestination(Long id, DestinationDto dto);

    HttpApiResponse<String> deleteDestinationById(Long id);
}
