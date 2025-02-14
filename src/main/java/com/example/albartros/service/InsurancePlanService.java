package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePlanDto;
import com.example.albartros.dto.InsurancePurchaseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsurancePlanService {

    HttpApiResponse<InsurancePlanDto> createInsurancePlan(InsurancePlanDto dto);
    HttpApiResponse<InsurancePlanDto> getInsurancePlanById(Long id);
    HttpApiResponse<InsurancePlanDto> updateInsurancePlanById(Long id, InsurancePlanDto dto);
    HttpApiResponse<InsurancePlanDto> deleteInsurancePlanById(Long id);
    HttpApiResponse<List<InsurancePlanDto>> getAllPlans();
    HttpApiResponse<List<InsurancePlanDto>> getAllPlansByCompany(Long companyId);

}
