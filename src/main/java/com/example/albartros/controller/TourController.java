package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Create Tour", description = "Create Tour")
    @ApiResponse(responseCode = "201", description = "Tour Created Successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "400", description = "Bad request")
    public HttpApiResponse<ToursDto> createTour(@RequestBody @Valid ToursDto dto) {
        return this.tourService.createTour(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Tour", description = "Find tour by Id")
    @ApiResponse(responseCode = "200", description = "Find Tour by Id")
    @ApiResponse(responseCode = "404", description = "Tour not found")
    public HttpApiResponse<ToursDto> getTourById(@PathVariable Long id) {
        return this.tourService.getTourById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find All-Tour", description = "Find all-tour")
    @ApiResponse(responseCode = "200", description = "Find Tour List")
    @ApiResponse(responseCode = "404", description = "Tour not found")
    public HttpApiResponse<List<ToursDto>> getAllTours() {
        return this.tourService.getAllTours();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Tour", description = "Update tour by Id")
    @ApiResponse(responseCode = "200", description = "Updated Tour Successfully")
    @ApiResponse(responseCode = "404", description = "Tour not found")
    @ApiResponse(responseCode = "400", description = "Something went wrong")
    public HttpApiResponse<ToursDto> updateTour(@PathVariable Long id, @RequestBody ToursDto dto) {
        return tourService.updateTourById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Tour", description = "Delete tour by Id")
    @ApiResponse(responseCode = "200", description = "Deleted Tour Successfully")
    @ApiResponse(responseCode = "404", description = "Tour not found")
    @ApiResponse(responseCode = "400", description = "Something went wrong")
    public HttpApiResponse<String> deleteTour(@PathVariable Long id) {
        return this.tourService.deleteTourById(id);
    }
}
