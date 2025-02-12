package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TourService {
    HttpApiResponse<ToursDto> createTour(ToursDto dto);

    HttpApiResponse<ToursDto> getTourById(Long id);

    HttpApiResponse<List<ToursDto>> getAllTours();

    HttpApiResponse<ToursDto> updateTourById(Long id, ToursDto dto);

    HttpApiResponse<String> deleteTourById(Long id);
}
