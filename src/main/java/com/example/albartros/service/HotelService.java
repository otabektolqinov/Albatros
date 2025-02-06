package com.example.albartros.service;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    HttpApiResponse<HotelDto> createHotel(HotelDto dto);

    HttpApiResponse<HotelDto> getHotelById(Long id);

    HttpApiResponse<HotelDto> updateHotel(Long id, HotelDto dto);

    HttpApiResponse<String> deleteHotel(Long id);

    HttpApiResponse<List<HotelDto>> getAllHotels();
}
