package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsuranceCompanyDto;
import com.example.albartros.model.InsuranceCompany;
import com.example.albartros.service.InsuranceCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-company/")
public class InsuranceCompanyController {

    private final InsuranceCompanyService companyService;

    @Operation(summary = "Create an Insurance Company", description = "Create an Insurance Company")
    @PostMapping
    public HttpApiResponse<InsuranceCompanyDto> createInsuranceCompany(@RequestBody InsuranceCompanyDto dto){
        return companyService.createInsuranceCompany(dto);
    }

    @Operation(summary = "Fetch an Insurance Company using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> getInsuranceCompanyById(@PathVariable("id") Long id){
        return companyService.getInsuranceCompanyById(id);
    }

    @Operation(summary = "Update Insurance Company by ID",
            description = "Update Insurance Company using its ID and giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> updateInsuranceCompanyById(@PathVariable("id") Long id,
                                                                           @RequestBody InsuranceCompanyDto dto){
        return companyService.updateInsuranceCompanyById(id, dto);
    }

    @Operation(summary = "Delete Insurance Company by ID", description = "Delete Insurance Company using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<InsuranceCompanyDto> deleteInsuranceCompanyById(@PathVariable("id") Long id){
        return companyService.deleteInsuranceCompanyById(id);
    }

    @Operation(summary = "Get all Countries", description = "Fetch All Countries")
    @GetMapping("/get-all")
    public HttpApiResponse<List<InsuranceCompanyDto>> getAllInsuranceCompanies(){
        return companyService.getAllInsuranceCompanies();
    }
}
