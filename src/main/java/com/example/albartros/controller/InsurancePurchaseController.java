package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePurchaseDto;
import com.example.albartros.model.InsurancePurchase;
import com.example.albartros.service.InsurancePurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-purchase/")
public class InsurancePurchaseController {

    private final InsurancePurchaseService purchaseService;

    @Operation(summary = "Create Insurance Purchase", description = "Add a new Insurance Purchase")
    @PostMapping
    public HttpApiResponse<InsurancePurchaseDto> createInsurancePurchase(@RequestBody InsurancePurchaseDto dto){
        return purchaseService.createInsurancePurchase(dto);
    }

    @Operation(summary = "Get an Insurance Purchase by ID", description = "Fetch Insurance Purchase using its ID")
    @GetMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseById(@PathVariable("id") Long id){
        return purchaseService.getInsurancePurchaseById(id);
    }

    @Operation(summary = "Get an Insurance Purchase of a User",
            description = "Fetch an Insurance Purchase of a User using user Id")
    @GetMapping("/by-user/{userId}")
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseByUserId(@PathVariable("userId") Long id){
        return purchaseService.getInsurancePurchaseByUserId(id);
    }

    @Operation(summary = "Update an Insurance Purchase by ID",
            description = "Update an Insurance Purchase using its ID")
    @PutMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> updateInsurancePurchaseById(@PathVariable("id") Long id,
                                                                             @RequestBody InsurancePurchaseDto dto){
        return purchaseService.updateInsurancePurchaseById(id, dto);
    }

    @Operation(summary = "Delete an Insurance Purchase By Id", description = "Delete an Insurance Purchase By ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> deleteInsurancePurchaseById(@PathVariable("id") Long id){
        return purchaseService.deleteInsurancePurchaseById(id);
    }

}
