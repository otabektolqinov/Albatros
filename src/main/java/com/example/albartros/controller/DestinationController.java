package com.example.albartros.controller;

import com.example.albartros.dto.DestinationDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.DestinationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("destination")
public class DestinationController {
    private final DestinationService destinationService;

    @PostMapping
    public HttpApiResponse<DestinationDto> createDestination(@RequestBody @Valid DestinationDto dto) {
        return this.destinationService.createDestination(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<DestinationDto> getDestinationById(@PathVariable Long id) {
        return this.destinationService.getDestinationById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<DestinationDto>> getAllDestinations() {
        return this.destinationService.getAllDestinations();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<DestinationDto> updateDestination(@PathVariable Long id, @RequestBody DestinationDto dto) {
        return this.destinationService.updateDestination(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteDestinationById(@PathVariable Long id) {
        return this.destinationService.deleteDestinationById(id);
    }
}
