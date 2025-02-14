package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.service.TourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tour")
public class TourController {
    private final TourService tourService;

    @PostMapping
    public HttpApiResponse<ToursDto> createTour(@RequestBody @Valid ToursDto dto) {
        return this.tourService.createTour(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<ToursDto> getTourById(@PathVariable Long id) {
        return this.tourService.getTourById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<ToursDto>> getAllTours() {
        return this.tourService.getAllTours();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<ToursDto> updateTour(@PathVariable Long id, @RequestBody ToursDto dto) {
        return tourService.updateTourById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteTour(@PathVariable Long id) {
        return this.tourService.deleteTourById(id);
    }
}
