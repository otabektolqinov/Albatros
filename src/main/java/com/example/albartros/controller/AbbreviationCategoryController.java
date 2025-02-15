package com.example.albartros.controller;

import com.example.albartros.dto.AbbreviationCategoryDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.AbbreviationCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("abbreviation-category/")
@RestController
public class AbbreviationCategoryController {

    @Qualifier("abbreviationCategoryService")
    private final AbbreviationCategoryService categoryService;


    @Operation(summary = "Create abbreviation category",
            description = "Add new abbreviation category to create abbreviations under this category")
    @ApiResponse(responseCode = "201", description = "Successfully created")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "403", description = "Not authorized")
    @PostMapping
    public HttpApiResponse<AbbreviationCategoryDto> createAbbrCategory(@RequestBody AbbreviationCategoryDto dto){
        return categoryService.createAbbreviationCategory(dto);
    }

    @Operation(summary = "Get an abbreviation category by id", description = "Fetch an abbreviationCategory using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<AbbreviationCategoryDto> getAbbrCategoryById(@PathVariable("id") Long id){
        return categoryService.getAbbrCategoryById(id);
    }


    @Operation(summary = "Update abbreviation category",
            description = "Update abbreviation category, enter the field and value to update")
    @PutMapping("/{id}")
    public HttpApiResponse<AbbreviationCategoryDto> updateAbbrCategoryById(@PathVariable("id") Long id,
                                                                           @RequestBody AbbreviationCategoryDto dto){
        return categoryService.updateAbbrCategoryById(id, dto);
    }

    @Operation(summary = "Delete abbreviation category by id",
            description = "Delete abbreviation category using its ID")
    @DeleteMapping("{id}")
    public HttpApiResponse<AbbreviationCategoryDto> deleteAbbCategoryById(@PathVariable("id") Long id){
        return categoryService.deleteAbbrCategoryById(id);
    }

}
