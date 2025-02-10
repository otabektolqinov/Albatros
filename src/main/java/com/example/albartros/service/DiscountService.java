package com.example.albartros.service;

import com.example.albartros.dto.DiscountDto;
import com.example.albartros.dto.HttpApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscountService {
    HttpApiResponse<DiscountDto> createDiscount(DiscountDto dto);

    HttpApiResponse<DiscountDto> getDiscountById(Long id);

    HttpApiResponse<List<DiscountDto>> getAllDiscounts();

    HttpApiResponse<DiscountDto> updateDiscountById(Long id, DiscountDto dto);

    HttpApiResponse<String> deleteDiscountById(Long id);
}
