package com.example.albartros.controller;

import com.example.albartros.dto.AbbreviationDto;
import com.example.albartros.dto.HttpApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.albartros.service.AbbreviationService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("abbreviation/")
public class AbbreviationController {

    private final AbbreviationService abbreviationService;

    @Operation(summary = "Create a new Abbreviation", description = "Add a new Abbreviation")
    @PostMapping
    public HttpApiResponse<AbbreviationDto> createAbbreviation(@RequestBody AbbreviationDto dto){
        return this.abbreviationService.createAbbreviation(dto);
    }

    @Operation(summary = "Get an Abbreviation by its ID", description = "Fetch an Abbreviation using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> getAbbreviationById(@PathVariable("id") Long id){
        return this.abbreviationService.getAbbreviationById(id);
    }

    @Operation(summary = "Update an Abbreviation by its ID",
            description = "Update an abbreviation using its ID, and by giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> updateAbbreviationById(@PathVariable("id") Long id,
                                                                   @RequestBody AbbreviationDto dto){
        return this.abbreviationService.updateAbbreviationById(id, dto);
    }

    @Operation(summary = "Delete An Abbreviation by its ID", description = "Delete an Abbreviation using its id")
    @DeleteMapping("/{id}")
    public HttpApiResponse<AbbreviationDto> deleteAbbreviationById(@PathVariable("id") Long id){
        return this.abbreviationService.deleteAbbreviationById(id);
    }

    @Operation(summary = "Get all abbreviations in particular category",
            description = "Get all abbreviations in a category by specifying a category id")
    @GetMapping("/get-all-by-category/{categoryId}")
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviationsByCategory(@PathVariable("categoryId") Long categoryId){
        return this.abbreviationService.getAllAbbreviationsByCategory(categoryId);
    }

    @Operation(summary = "Get all Abbreviations", description = "Fetch all Abbreviations")
    @GetMapping("/get-all")
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviations(){
        return this.abbreviationService.getAllAbbreviations();
    }

}
