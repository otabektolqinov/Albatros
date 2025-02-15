package com.example.albartros.controller;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("hotel/")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @Operation(summary = "Create Hotel", description = "Create hotel")
    @ApiResponse(responseCode = "201", description = "Hotel created successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    public HttpApiResponse<HotelDto> createHotel(@RequestBody @Valid HotelDto dto) {
        return this.hotelService.createHotel(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find hotel by Id", description = "Find hotel by id")
    public HttpApiResponse<HotelDto> getHotelById(@PathVariable Long id) {
        return this.hotelService.getHotelById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find all hotels", description = "Get Hotels list")
    public HttpApiResponse<List<HotelDto>> getAllHotels() {
        return this.hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update hotel", description = "Update hotel by id")
    public HttpApiResponse<HotelDto> updateHotel(@PathVariable Long id, @RequestBody HotelDto dto) {
        return this.hotelService.updateHotel(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hotel", description = "Delete Hotel by Id")
    public HttpApiResponse<String> deleteHotel(@PathVariable Long id) {
        return this.hotelService.deleteHotel(id);
    }

}
