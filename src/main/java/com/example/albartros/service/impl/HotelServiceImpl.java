package com.example.albartros.service.impl;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.model.Hotel;
import com.example.albartros.repository.HotelRepository;
import com.example.albartros.service.HotelService;
import com.example.albartros.service.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HttpApiResponse<HotelDto> createHotel(HotelDto dto) {
        //check exist country
        try {
            return HttpApiResponse.<HotelDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("Hotel created successfully")
                    .content(this.hotelMapper.toDto(
                            this.hotelRepository.saveAndFlush(
                                    this.hotelMapper.toEntity(dto))))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public HttpApiResponse<HotelDto> getHotelById(Long id) {
        try {
            if (this.hotelRepository.existsById(id)) {
                Optional<Hotel> hotel = this.hotelRepository.findByIdAndDeletedAtIsNull(id);
                if (hotel.isPresent()) {
                    return HttpApiResponse.<HotelDto>builder()
                            .status(HttpStatus.OK)
                            .message("Ok")
                            .content(this.hotelMapper.toDto(hotel.get()))
                            .build();
                }
            }
            return HttpApiResponse.<HotelDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Hotel not found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<HotelDto> updateHotel(Long id, HotelDto dto) {
        try {
            if (this.hotelRepository.existsById(id)) {
                Optional<Hotel> hotel = this.hotelRepository.findByIdAndDeletedAtIsNull(id);
                if (hotel.isPresent()) {
                    Hotel updatedHotel = this.hotelMapper.updateHotelAllField(hotel.get(), dto);
                    this.hotelRepository.saveAndFlush(updatedHotel);
                    return HttpApiResponse.<HotelDto>builder()
                            .status(HttpStatus.OK)
                            .message("Hotel updated successfully")
                            .content(this.hotelMapper.toDto(updatedHotel))
                            .build();
                }
            }
            return HttpApiResponse.<HotelDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Hotel not found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<String> deleteHotel(Long id) {
        try {
            if (this.hotelRepository.existsById(id)) {
                Optional<Hotel> hotel = this.hotelRepository.findByIdAndDeletedAtIsNull(id);
                if (hotel.isPresent()) {
                    hotel.get().setDeletedAt(LocalDateTime.now());
                    this.hotelRepository.saveAndFlush(hotel.get());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Hotel deleted successfully")
                            .build();
                }
            }
            return HttpApiResponse.<String>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Hotel not found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<List<HotelDto>> getAllHotels() {
        if (!this.hotelRepository.findAllByDeletedAtIsNull().isEmpty()) {
            return HttpApiResponse.<List<HotelDto>>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(this.hotelMapper.toDtoList(getHotelList()))
                    .build();
        }
        return HttpApiResponse.<List<HotelDto>>builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Hotels List Empty")
                .build();
    }


    public List<Hotel> getHotelList() {
        return this.hotelRepository.findAllByDeletedAtIsNull();
    }
}
