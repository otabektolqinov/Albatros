package com.example.albartros.service.mapper;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotelMapper {


    public HotelDto toDto(Hotel hotel) {
        return HotelDto.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .cityName(hotel.getCityName())
                .starts(hotel.getStarts())
//                .countryId(hotel.getCountry().getId())
                .createdAt(hotel.getCreatedAt())
                .updatedAt(hotel.getUpdatedAt())
                .deletedAt(hotel.getDeletedAt())
                .build();
    }

    public Hotel toEntity(HotelDto dto) {
        return Hotel.builder()
                .name(dto.getName())
                .cityName(dto.getCityName())
                .starts(dto.getStarts())
                .build();
    }

    public Hotel updateHotelAllField(Hotel hotel, HotelDto dto) {
        if (dto.getName() != null) {
            hotel.setName(dto.getName());
        }
        if (dto.getCityName() != null) {
            hotel.setCityName(dto.getCityName());
        }
        if (dto.getStarts() != null) {
            hotel.setStarts(dto.getStarts());
        }
        return hotel;
    }

    public List<HotelDto> toDtoList(List<Hotel> hotels) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDtoList.add(toDto(hotel));
        }
        return hotelDtoList;
    }
}
