package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsuranceCompanyDto;
import com.example.albartros.model.InsuranceCompany;
import com.example.albartros.service.InsuranceCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-company")
public class InsuranceCompanyController {

    private final InsuranceCompanyService companyService;

    @PostMapping
    public HttpApiResponse<InsuranceCompanyDto> createInsuranceCompany(@RequestBody InsuranceCompanyDto dto){
        return companyService.createInsuranceCompany(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> getInsuranceCompanyById(@PathVariable("id") Long id){
        return companyService.getInsuranceCompanyById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> updateInsuranceCompanyById(@PathVariable("id") Long id,
                                                                           @RequestBody InsuranceCompanyDto dto){
        return companyService.updateInsuranceCompanyById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> deleteInsuranceCompanyById(@PathVariable("id") Long id){
        return companyService.deleteInsuranceCompanyById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<InsuranceCompanyDto>> getAllInsuranceCompanies(){
        return companyService.getAllInsuranceCompanies();
    }
}
