package com.example.albartros.service;

import com.example.albartros.dto.BookingDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    HttpApiResponse<BookingDto> createBooking(BookingDto dto);

    HttpApiResponse<BookingDto> getBookingById(Long id);

    HttpApiResponse<List<BookingDto>> getAllBookings();

    HttpApiResponse<BookingDto> updateBooking(Long id, BookingDto dto);

    HttpApiResponse<String> deleteBookingById(Long id);

    HttpApiResponse<BookingDto> createBookingByUser(Long userId, BookingDto dto);
}
