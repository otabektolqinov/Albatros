package com.example.albartros.controller;

import com.example.albartros.dto.CoverageDetailsDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.CoverageDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("coverage-details/")
public class CoverageDetailsController {

    private final CoverageDetailsService detailsService;

    @Operation(summary = "Create a Coverage details",
            description = "Add a coverage details for an Insurance Plan")
    @PostMapping
    public HttpApiResponse<CoverageDetailsDto> createCoverageDetails(@RequestBody CoverageDetailsDto dto){
        return detailsService.createCoverageDetails(dto);
    }

    @Operation(summary = "Get a Coverage Details by ID", description = "Fetch a Coverage Details using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> getCoverageDetailsById(@PathVariable("id") Long id){
        return detailsService.getCoverageDetailsById(id);
    }

    @Operation(summary = "Update a Coverage Details",
            description = "Update a Coverage details by using its id, and giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> updateCoverageDetailsById(@PathVariable("id") Long id,
                                                                         @RequestBody CoverageDetailsDto dto){
        return detailsService.updateCoverageDetailsById(id, dto);
    }

    @Operation(summary = "Delete a Coverage Details by ID", description = "Delete a Coverage Details By using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> deleteCoverageDetailsById(@PathVariable("id") Long id){
        return detailsService.deleteCoverageDetailsById(id);
    }

}
