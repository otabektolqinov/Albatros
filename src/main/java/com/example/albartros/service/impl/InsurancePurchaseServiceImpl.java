package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePurchaseDto;
import com.example.albartros.enums.InsuranceStatus;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.InsurancePlan;
import com.example.albartros.model.InsurancePurchase;
import com.example.albartros.repository.InsurancePlanRepository;
import com.example.albartros.repository.InsurancePurchaseRepository;
import com.example.albartros.service.InsurancePurchaseService;
import com.example.albartros.service.mapper.InsurancePurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsurancePurchaseServiceImpl implements InsurancePurchaseService {

    private final InsurancePurchaseMapper purchaseMapper;
    private final InsurancePurchaseRepository purchaseRepository;
    private final InsurancePlanRepository insurancePlanRepository;


    @Override
    public HttpApiResponse<InsurancePurchaseDto> createInsurancePurchase(InsurancePurchaseDto dto) {
        try {
            if (insurancePlanRepository.findByIdAndDeletedAtIsNull(dto.getInsurancePlanId()).isEmpty()){
                throw new ContentNotFoundException(String.format("There is no plan with %d id", dto.getInsurancePlanId()));
            }
            InsurancePurchase entity = purchaseMapper.toEntity(dto);
            InsurancePurchase saved = purchaseRepository.save(entity);

            return HttpApiResponse.<InsurancePurchaseDto>builder()
                    .content(purchaseMapper.toDto(saved))
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseById(Long id) {
        try {
            Optional<InsurancePurchase> optional = purchaseRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("InsurancePurchase with %d id is not found", id));
            }

            return HttpApiResponse.<InsurancePurchaseDto>builder()
                    .status(HttpStatus.OK)
                    .content(purchaseMapper.toDto(optional.get()))
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePurchaseDto> getInsurancePurchaseByUserId(Long id) {
        try {
            Optional<InsurancePurchase> optional = purchaseRepository.findByBookings_id(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("InsurancePurchase with %d booking id is not found", id));
            }

            return HttpApiResponse.<InsurancePurchaseDto>builder()
                    .content(purchaseMapper.toDto(optional.get()))
                    .status(HttpStatus.OK)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePurchaseDto> updateInsurancePurchaseById(Long id, InsurancePurchaseDto dto) {
        try {
            Optional<InsurancePurchase> optional = purchaseRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("InsurancePurchase with %d id is not found", id));
            }

            InsurancePurchase insurancePurchase = purchaseMapper.updateAllFields(optional.get(), dto);
            InsurancePurchase updated = purchaseRepository.save(insurancePurchase);

            return HttpApiResponse.<InsurancePurchaseDto>builder()
                    .status(HttpStatus.OK)
                    .content(purchaseMapper.toDto(updated))
                    .message("Successfully updated")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePurchaseDto> deleteInsurancePurchaseById(Long id) {
        try {
            Optional<InsurancePurchase> optional = purchaseRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("InsurancePurchase with %d id is not found", id));
            }

            optional.get().setDeletedAt(LocalDateTime.now());
            optional.get().setStatus(InsuranceStatus.EXPIRED);
            purchaseRepository.save(optional.get());

            return HttpApiResponse.<InsurancePurchaseDto>builder()
                    .message("Successfully deleted")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
