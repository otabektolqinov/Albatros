package com.example.albartros.controller;

import com.example.albartros.dto.CoverageDetailsDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.CoverageDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("coverage-details/")
public class CoverageDetailsController {

    private final CoverageDetailsService detailsService;

    @PostMapping
    public HttpApiResponse<CoverageDetailsDto> createCoverageDetails(@RequestBody CoverageDetailsDto dto){
        return detailsService.createCoverageDetails(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> getCoverageDetailsById(@PathVariable("id") Long id){
        return detailsService.getCoverageDetailsById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> updateCoverageDetailsById(@PathVariable("id") Long id,
                                                                         @RequestBody CoverageDetailsDto dto){
        return detailsService.updateCoverageDetailsById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<CoverageDetailsDto> deleteCoverageDetailsById(@PathVariable("id") Long id){
        return detailsService.deleteCoverageDetailsById(id);
    }

}
