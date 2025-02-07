package com.example.albartros.service.impl;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.CustomExceptionHandler;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.model.Hotel;
import com.example.albartros.repository.CountryRepository;
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
    private final CountryRepository countryRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HttpApiResponse<HotelDto> createHotel(HotelDto dto) {
        if (!countryRepository.existsByIdAndDeletedAtIsNull(dto.getCountryId())) {
            throw new ContentNotFoundException("Country not found");
        }
        try {
            Optional<Country> country = countryRepository.findByIdAndDeletedAtIsNull(dto.getCountryId());
            if (country.isPresent()) {
                Hotel entity = this.hotelMapper.toEntity(dto);

                entity.setCountry(country.get());
                this.hotelRepository.save(entity);

                return HttpApiResponse.<HotelDto>builder()
                        .status(HttpStatus.CREATED)
                        .message("Hotel created successfully")
                        .content(this.hotelMapper.toDto(entity))
                        .build();
            }
            return HttpApiResponse.<HotelDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Country not found")
                    .build();

        } catch (DatabaseException e) {
            throw new DatabaseException("Unable to create hotel");
        }

    }

    @Override
    public HttpApiResponse<HotelDto> getHotelById(Long id) {
        try {
            if (this.hotelRepository.existsByIdAndDeletedAtIsNull(id)) {
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
            throw new ContentNotFoundException("Hotel not found");
        }
    }

    @Override
    public HttpApiResponse<HotelDto> updateHotel(Long id, HotelDto dto) {
        try {
            if (this.hotelRepository.existsByIdAndDeletedAtIsNull(id)) {
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
            throw new DatabaseException("Unable to update hotel");
        }
    }

    @Override
    public HttpApiResponse<String> deleteHotel(Long id) {
        try {
            if (this.hotelRepository.existsByIdAndDeletedAtIsNull(id)) {
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
