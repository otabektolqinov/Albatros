package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePlanDto;
import com.example.albartros.service.InsurancePlanService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("insurance-plan/")
public class InsurancePlanController {

    private final InsurancePlanService planService;

    @Operation(summary = "Create an Insurance Plan", description = "Add a new Insurance Plan")
    @PostMapping
    public HttpApiResponse<InsurancePlanDto> createInsurancePlan(@RequestBody InsurancePlanDto dto){
        return planService.createInsurancePlan(dto);
    }

    @Operation(summary = "Get an Insurance Plan by Id", description = "Fetch an Insurance Plan using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> getInsuranceCompanyById(@PathVariable("id") Long id){
        return planService.getInsurancePlanById(id);
    }

    @Operation(summary = "Update Insurance plan",
            description = "Update Insurance Plan using its and giving the field and value")
    @PutMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> updateInsurancePlanById(@PathVariable("id") Long id,
                                                                     @RequestBody InsurancePlanDto dto){
        return planService.updateInsurancePlanById(id, dto);
    }

    @Operation(summary = "Delete an Insurance Plan By Id", description = "Delete an Insurance Plan using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<InsurancePlanDto> deleteInsurancePlanById(@PathVariable("id") Long id){
        return planService.deleteInsurancePlanById(id);
    }

    @Operation(summary = "Get all Insurance Plans", description = "Fetch all insurance plans")
    @GetMapping("/get-all")
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlans(){
        return planService.getAllPlans();
    }

    @Operation(summary = "Get all Insurance Plan By Company", description = "Fetch all insurance plans of a Company")
    @GetMapping("/get-all-by-company/{companyId}")
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlansByCompany(@PathVariable("companyId") Long id){
        return planService.getAllPlansByCompany(id);
    }

}
