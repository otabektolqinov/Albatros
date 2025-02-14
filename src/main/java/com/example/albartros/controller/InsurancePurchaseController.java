package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePurchaseDto;
import com.example.albartros.model.InsurancePurchase;
import com.example.albartros.service.InsurancePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("insurance-purchase/")
public class InsurancePurchaseController {

    private final InsurancePurchaseService purchaseService;

    @PostMapping
    public HttpApiResponse<InsurancePurchaseDto> createInsurancePurchase(@RequestBody InsurancePurchaseDto dto){
        return purchaseService.createInsurancePurchase(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseById(@PathVariable("id") Long id){
        return purchaseService.getInsurancePurchaseById(id);
    }

    @GetMapping("/by-user/{userId}")
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseByUserId(@PathVariable("userId") Long id){
        return purchaseService.getInsurancePurchaseByUserId(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> updateInsurancePurchaseById(@PathVariable("id") Long id,
                                                                             @RequestBody InsurancePurchaseDto dto){
        return purchaseService.updateInsurancePurchaseById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<InsurancePurchaseDto> deleteInsurancePurchaseById(@PathVariable("id") Long id){
        return purchaseService.deleteInsurancePurchaseById(id);
    }

}
