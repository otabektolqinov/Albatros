package com.example.albartros.controller;

import com.example.albartros.dto.DiscountDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("discount/")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    @Operation(summary = "Create Discount", description = "Create discount")
    @ApiResponse(responseCode = "201", description = "Discount created successfully")
    @ApiResponse(responseCode = "404", description = "Authentication failed")
    public HttpApiResponse<DiscountDto> createDiscount(@RequestBody @Valid DiscountDto dto) {
        return this.discountService.createDiscount(dto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Discount", description = "Get discount by Id")
    @ApiResponse(responseCode = "200", description = "Discount retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Discount not found")
    public HttpApiResponse<DiscountDto> getDiscountById(@PathVariable("id") Long id) {
        return this.discountService.getDiscountById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get Discounts", description = "Get discountList")
    @ApiResponse(responseCode = "200", description = "Discounts retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Discounts not found")
    public HttpApiResponse<List<DiscountDto>> getAllDiscounts() {
        return this.discountService.getAllDiscounts();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Discount", description = "Update Discount ById")
    @ApiResponse(responseCode = "200", description = "Discount Updated Successfully")
    @ApiResponse(responseCode = "400", description = "Error while updating")
    @ApiResponse(responseCode = "404", description = "Discount not found")
    public HttpApiResponse<DiscountDto> updateDiscount(@PathVariable("id") Long id, @RequestBody DiscountDto dto) {
        return this.discountService.updateDiscountById(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Discount", description = "Delete Discount ById")
    @ApiResponse(responseCode = "200", description = "Discount deleted Successfully")
    @ApiResponse(responseCode = "400", description = "Error while deleting")
    @ApiResponse(responseCode = "404", description = "Discount not found")
    public HttpApiResponse<String> deleteDiscount(@PathVariable("id") Long id) {
        return this.discountService.deleteDiscountById(id);
    }
}
