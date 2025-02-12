package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsuranceCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsuranceCompanyService {

    HttpApiResponse<InsuranceCompanyDto> createInsuranceCompany(InsuranceCompanyDto dto);
    HttpApiResponse<InsuranceCompanyDto> getInsuranceCompanyById(Long id);
    HttpApiResponse<InsuranceCompanyDto> updateInsuranceCompanyById(Long id, InsuranceCompanyDto dto);
    HttpApiResponse<InsuranceCompanyDto> deleteInsuranceCompanyById(Long id);
    HttpApiResponse<List<InsuranceCompanyDto>> getAllInsuranceCompanies();

}
