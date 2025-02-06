package com.example.albartros.controller;

import com.example.albartros.dto.HotelDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.HotelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("hotel")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public HttpApiResponse<HotelDto> createHotel(@RequestBody HotelDto dto) {
        return this.hotelService.createHotel(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<HotelDto> getHotelById(@PathVariable Long id) {
        return this.hotelService.getHotelById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<HotelDto>> getAllHotels() {
        return this.hotelService.getAllHotels();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<HotelDto> updateHotel(@PathVariable Long id, @RequestBody HotelDto dto) {
        return this.hotelService.updateHotel(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteHotel(@PathVariable Long id) {
        return this.hotelService.deleteHotel(id);
    }

}
