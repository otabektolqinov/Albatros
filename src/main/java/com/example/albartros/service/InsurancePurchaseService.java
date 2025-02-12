package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePurchaseDto;
import org.springframework.stereotype.Service;

@Service
public interface InsurancePurchaseService {

    HttpApiResponse<InsurancePurchaseDto> createInsurancePurchase(InsurancePurchaseDto dto);
    HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseById(Long id);
    HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseByUserId(Long id);
    HttpApiResponse<InsurancePurchaseDto> updateInsurancePurchaseById(Long id, InsurancePurchaseDto dto);
    HttpApiResponse<InsurancePurchaseDto> deleteInsurancePurchaseById(Long id);

}
