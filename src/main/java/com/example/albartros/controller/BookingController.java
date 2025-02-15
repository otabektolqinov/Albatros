package com.example.albartros.controller;

import com.example.albartros.dto.BookingDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{userId}")
    @Operation(summary = "Create Booking", description = "Create Booking by UserId")
    @ApiResponse(responseCode = "201", description = "Booking created successfully")
    @ApiResponse(responseCode = "400", description = "Error while creating booking")
    @ApiResponse(responseCode = "404", description = "User not found")
    public HttpApiResponse<BookingDto> createBookingByUser(@PathVariable Long userId,
                                                           @RequestBody @Valid BookingDto dto) {
        return this.bookingService.createBookingByUser(userId, dto);
    }

    @PostMapping
    @Operation(summary = "Create Booking", description = "Create Booking ")
    @ApiResponse(responseCode = "201", description = "Booking created successfully")
    @ApiResponse(responseCode = "400", description = "Error while creating booking")
    @ApiResponse(responseCode = "404", description = "User not found")
    public HttpApiResponse<BookingDto> createBooking(@RequestBody BookingDto dto) {
        return this.bookingService.createBooking(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Booking", description = "Find Booking by iD")
    @ApiResponse(responseCode = "200", description = "Booking find successfully")
    @ApiResponse(responseCode = "400", description = "Error while getting booking")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    public HttpApiResponse<BookingDto> getBookingById(@PathVariable Long id) {
        return this.bookingService.getBookingById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find Bookings", description = "Find Bookings")
    @ApiResponse(responseCode = "200", description = "Bookings find successfully")
    @ApiResponse(responseCode = "400", description = "Error while getting bookings")
    @ApiResponse(responseCode = "404", description = "Bookings not found")
    public HttpApiResponse<List<BookingDto>> getAllBookings() {
        return this.bookingService.getAllBookings();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Booking", description = "Update Booking by iD")
    @ApiResponse(responseCode = "200", description = "Booking updated successfully")
    @ApiResponse(responseCode = "400", description = "Error while updating booking")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    public HttpApiResponse<BookingDto> updateBooking(@PathVariable Long id, @RequestBody BookingDto dto) {
        return this.bookingService.updateBooking(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Booking", description = "Delete Booking by iD")
    @ApiResponse(responseCode = "200", description = "Booking deleted successfully")
    @ApiResponse(responseCode = "400", description = "Error while deleting booking")
    @ApiResponse(responseCode = "404", description = "Booking not found")
    public HttpApiResponse<String> deleteBooking(@PathVariable Long id) {
        return this.bookingService.deleteBookingById(id);
    }
}
