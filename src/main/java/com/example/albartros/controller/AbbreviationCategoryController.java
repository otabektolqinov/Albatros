package com.example.albartros.controller;

import com.example.albartros.dto.AbbreviationCategoryDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.enums.UserRole;
import com.example.albartros.service.AbbreviationCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("abbreviation-category")
@RestController
public class AbbreviationCategoryController {

    private final AbbreviationCategoryService categoryService;

    @PostMapping
    public HttpApiResponse<AbbreviationCategoryDto> createAbbrCategory(@RequestBody AbbreviationCategoryDto dto){
        return categoryService.createAbbreviationCategory(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<AbbreviationCategoryDto> getAbbrCategoryById(@PathVariable("id") Long id){
        return categoryService.getAbbrCategoryById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<AbbreviationCategoryDto> updateAbbrCategoryById(@PathVariable("id") Long id,
                                                                           @RequestBody AbbreviationCategoryDto dto){
        return categoryService.updateAbbrCategoryById(id, dto);
    }

    @DeleteMapping("{id}")
    public HttpApiResponse<AbbreviationCategoryDto> deleteAbbCategoryById(@PathVariable("id") Long id){
        return categoryService.deleteAbbrCategoryById(id);
    }

}
