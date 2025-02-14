package com.example.albartros.controller;

import com.example.albartros.dto.AgencyDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("agency/")
public class AgencyController {
    private final AgencyService agencyService;

    @PostMapping
    public HttpApiResponse<AgencyDto> createAgency(@RequestBody AgencyDto dto) {
        return this.agencyService.createAgency(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<AgencyDto> getAgencyById(@PathVariable Long id) {
        return this.agencyService.getAgencyById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<AgencyDto>> getAllAgency() {
        return this.agencyService.getAllAgency();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<AgencyDto> updateTours(@PathVariable Long id, @RequestBody AgencyDto dto) {
        return this.agencyService.updateTourById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteTour(@PathVariable Long id) {
        return this.agencyService.deleteTourById(id);
    }
}
