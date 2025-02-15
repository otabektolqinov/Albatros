package com.example.albartros.controller;

import com.example.albartros.dto.FactDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.FactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("fact/")
public class FactController {
    private final FactService factService;

    @PostMapping
    @Operation(summary = "Create Fact", description = "Create Fact for Country")
    @ApiResponse(responseCode = "201", description = "Fact created successfully")
    @ApiResponse(responseCode = "404", description = "Country not found")
    @ApiResponse(responseCode = "40o", description = "Error occurred")
    public HttpApiResponse<FactDto> createFact(@RequestBody @Valid FactDto dto) {
        return this.factService.createFact(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Fact", description = "Find fact By Id")
    @ApiResponse(responseCode = "200", description = "Fact retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "400", description = "Error occurred")
    public HttpApiResponse<FactDto> getFactById(@PathVariable Long id) {
        return this.factService.getFactById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find Facts", description = "Finds factList")
    @ApiResponse(responseCode = "200", description = "Facts retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "400", description = "Error occurred")
    public HttpApiResponse<List<FactDto>> getAllFacts() {
        return this.factService.getAllFacts();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Fact", description = "Update fact By Id")
    @ApiResponse(responseCode = "200", description = "Fact Updated successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "400", description = "Error occurred")
    public HttpApiResponse<FactDto> updateFact(@PathVariable Long id, @RequestBody FactDto dto) {
        return this.factService.updateFact(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Fact", description = "Delete fact By Id")
    @ApiResponse(responseCode = "200", description = "Fact deleted successfully")
    @ApiResponse(responseCode = "404", description = "Not found")
    @ApiResponse(responseCode = "400", description = "Error occurred")
    public HttpApiResponse<String> deleteFactById(@PathVariable Long id) {
        return this.factService.deleteFactById(id);
    }
}
