package com.example.albartros.controller;

import com.example.albartros.dto.BookingDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{userId}")
    public HttpApiResponse<BookingDto> createBookingByUser(@PathVariable Long userId, @RequestBody BookingDto dto) {
        return this.bookingService.createBookingByUser(userId, dto);
    }

    @PostMapping
    public HttpApiResponse<BookingDto> createBooking(@RequestBody BookingDto dto) {
        return this.bookingService.createBooking(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<BookingDto> getBookingById(@PathVariable Long id) {
        return this.bookingService.getBookingById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<BookingDto>> getAllBookings() {
        return this.bookingService.getAllBookings();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto dto) {
        return this.bookingService.updateBooking(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteBooking(@PathVariable Long id) {
        return this.bookingService.deleteBookingById(id);
    }
}
