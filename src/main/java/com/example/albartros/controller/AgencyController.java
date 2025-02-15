package com.example.albartros.controller;

import com.example.albartros.dto.AgencyDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ToursDto;
import com.example.albartros.service.AgencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("agency")
public class AgencyController {
    private final AgencyService agencyService;

    @PostMapping
    @Operation(summary = "Create Agency", description = "Create Agency")
    @ApiResponse(responseCode = "201", description = "Agency created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "403", description = "Authentication failed")
    public HttpApiResponse<AgencyDto> createAgency(@RequestBody AgencyDto dto) {
        return this.agencyService.createAgency(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Agency", description = "Find Agency by id")
    @ApiResponse(responseCode = "200",description = "Agency retrieved successfully")
    @ApiResponse(responseCode = "404",description = "Agency not found")
    public HttpApiResponse<AgencyDto> getAgencyById(@PathVariable Long id) {
        return this.agencyService.getAgencyById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find AgencyList", description = "Find AgencyList")
    @ApiResponse(responseCode = "200",description = "AgencyList retrieved successfully")
    @ApiResponse(responseCode = "404",description = "AgencyList not found")
    public HttpApiResponse<List<AgencyDto>> getAllAgency() {
        return this.agencyService.getAllAgency();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Agency", description = "Update Agency by id")
    @ApiResponse(responseCode = "200",description = "Agency updated successfully")
    @ApiResponse(responseCode = "404",description = "Agency not found")
    @ApiResponse(responseCode = "400",description = "Bad Request")
    public HttpApiResponse<AgencyDto> updateTours(@PathVariable Long id, @RequestBody @Valid AgencyDto dto) {
        return this.agencyService.updateTourById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Agency", description = "Delete Agency by id")
    @ApiResponse(responseCode = "200",description = "Agency deleted successfully")
    @ApiResponse(responseCode = "404",description = "Agency not found")
    @ApiResponse(responseCode = "400",description = "Bad Request")
    public HttpApiResponse<String> deleteTour(@PathVariable Long id) {
        return this.agencyService.deleteTourById(id);
    }
}
