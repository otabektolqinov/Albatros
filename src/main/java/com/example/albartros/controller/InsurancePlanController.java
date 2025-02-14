package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePlanDto;
import com.example.albartros.service.InsurancePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("insurance-plan")
public class InsurancePlanController {

    private final InsurancePlanService planService;

    @PostMapping
    public HttpApiResponse<InsurancePlanDto> createInsurancePlan(@RequestBody InsurancePlanDto dto){
        return planService.createInsurancePlan(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> getInsuranceCompanyById(@PathVariable("id") Long id){
        return planService.getInsurancePlanById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> updateInsurancePlanById(@PathVariable("id") Long id,
                                                                     @RequestBody InsurancePlanDto dto){
        return planService.updateInsurancePlanById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> deleteInsurancePlanById(@PathVariable("id") Long id){
        return planService.deleteInsurancePlanById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlans(){
        return planService.getAllPlans();
    }

    @GetMapping("/get-all-by-company/{companyId}")
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlansByCompany(@PathVariable("companyId") Long id){
        return planService.getAllPlansByCompany(id);
    }

}
