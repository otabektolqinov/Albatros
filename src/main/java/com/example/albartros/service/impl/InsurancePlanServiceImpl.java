package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsurancePlanDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.InsurancePlan;
import com.example.albartros.repository.InsurancePlanRepository;
import com.example.albartros.service.InsurancePlanService;
import com.example.albartros.service.mapper.InsurancePlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsurancePlanServiceImpl implements InsurancePlanService {

    private final InsurancePlanMapper planMapper;
    private final InsurancePlanRepository planRepository;

    @Override
    public HttpApiResponse<InsurancePlanDto> createInsurancePlan(InsurancePlanDto dto) {
        try {
            InsurancePlan entity = planMapper.toEntity(dto);
            InsurancePlan saved = planRepository.save(entity);

            return HttpApiResponse.<InsurancePlanDto>builder()
                    .content(planMapper.toDto(saved))
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePlanDto> getInsurancePlanById(Long id) {
        try {
            Optional<InsurancePlan> plan = planRepository.findByIdAndDeletedAtIsNull(id);
            if (plan.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Plan with %d id is not found", id));
            }

            return HttpApiResponse.<InsurancePlanDto>builder()
                    .status(HttpStatus.OK)
                    .content(planMapper.toDto(plan.get()))
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePlanDto> updateInsurancePlanById(Long id, InsurancePlanDto dto) {
        try {
            Optional<InsurancePlan> plan = planRepository.findByIdAndDeletedAtIsNull(id);
            if (plan.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Plan with %d id is not found", id));
            }

            InsurancePlan insurancePlan = planMapper.updateAllFields(plan.get(), dto);
            InsurancePlan saved = planRepository.save(insurancePlan);

            return HttpApiResponse.<InsurancePlanDto>builder()
                    .content(planMapper.toDto(saved))
                    .status(HttpStatus.OK)
                    .message("Successfully updated")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsurancePlanDto> deleteInsurancePlanById(Long id) {
        try {
            Optional<InsurancePlan> plan = planRepository.findByIdAndDeletedAtIsNull(id);
            if (plan.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Plan with %d id is not found", id));
            }

            plan.get().setDeletedAt(LocalDateTime.now());
            planRepository.save(plan.get());

            return HttpApiResponse.<InsurancePlanDto>builder()
                    .status(HttpStatus.OK)
                    .message("Successfully deleted")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlans() {
        try {
            List<InsurancePlan> plans = planRepository.findAllByDeletedAtIsNull();
            if (plans.isEmpty()){
                throw new ContentNotFoundException("Insurance Plan list is empty");
            }

            return HttpApiResponse.<List<InsurancePlanDto>>builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .content(planMapper.toDtoList(plans))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<InsurancePlanDto>> getAllPlansByCompany(Long companyId) {
        try {
            List<InsurancePlan> planList = planRepository.findAllByDeletedAtIsNullAndInsuranceCompany_Id(companyId);
            if (planList.isEmpty()){
                throw new ContentNotFoundException("Insurance Plan list is empty");
            }

            return HttpApiResponse.<List<InsurancePlanDto>>builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .content(planMapper.toDtoList(planList))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
