package com.example.albartros.controller;

import com.example.albartros.dto.DiscountDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("discount")
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping
    public HttpApiResponse<DiscountDto> createDiscount(@RequestBody @Valid DiscountDto dto) {
        return this.discountService.createDiscount(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<DiscountDto> getDiscountById(@PathVariable("id") Long id) {
        return this.discountService.getDiscountById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<DiscountDto>> getAllDiscounts() {
        return this.discountService.getAllDiscounts();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<DiscountDto> updateDiscount(@PathVariable("id") Long id, @RequestBody DiscountDto dto) {
        return this.discountService.updateDiscountById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteDiscount(@PathVariable("id") Long id) {
        return this.discountService.deleteDiscountById(id);
    }
}
