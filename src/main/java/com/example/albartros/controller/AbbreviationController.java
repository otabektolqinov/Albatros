package com.example.albartros.controller;

import com.example.albartros.dto.AbbreviationDto;
import com.example.albartros.dto.HttpApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.albartros.service.AbbreviationService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("abbreviation")
public class AbbreviationController {

    private final AbbreviationService abbreviationService;

    @PostMapping
    public HttpApiResponse<AbbreviationDto> createAbbreviation(@RequestBody AbbreviationDto dto){
        return this.abbreviationService.createAbbreviation(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> getAbbreviationById(@PathVariable("id") Long id){
        return this.abbreviationService.getAbbreviationById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> updateAbbreviationById(@PathVariable("id") Long id,
                                                                   @RequestBody AbbreviationDto dto){
        return this.abbreviationService.updateAbbreviationById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> deleteAbbreviationById(@PathVariable("id") Long id){
        return this.abbreviationService.deleteAbbreviationById(id);
    }

    @GetMapping("/get-all-by-category/{categoryId}")
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviationsByCategory(@PathVariable("categoryId") Long categoryId){
        return this.abbreviationService.getAllAbbreviationsByCategory(categoryId);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviations(){
        return this.abbreviationService.getAllAbbreviations();
    }

}
