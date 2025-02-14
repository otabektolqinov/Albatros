package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.InsuranceCompanyDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.InsuranceCompany;
import com.example.albartros.repository.InsuranceCompanyRepository;
import com.example.albartros.service.InsuranceCompanyService;
import com.example.albartros.service.mapper.InsuranceCompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {

    private final InsuranceCompanyMapper companyMapper;
    private final InsuranceCompanyRepository companyRepository;

    @Override
    public HttpApiResponse<InsuranceCompanyDto> createInsuranceCompany(InsuranceCompanyDto dto) {
        try {
            InsuranceCompany entity = companyMapper.toEntity(dto);
            InsuranceCompany saved = companyRepository.save(entity);

            return HttpApiResponse.<InsuranceCompanyDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(companyMapper.toDto(saved))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsuranceCompanyDto> getInsuranceCompanyById(Long id) {
        try {
            Optional<InsuranceCompany> optional = companyRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Company with %d id is not found", id));
            }

            return HttpApiResponse.<InsuranceCompanyDto>builder()
                    .content(companyMapper.toDtoWithAllEntity(optional.get()))
                    .status(HttpStatus.OK)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsuranceCompanyDto> updateInsuranceCompanyById(Long id, InsuranceCompanyDto dto) {
        try {
            Optional<InsuranceCompany> optional = companyRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Company with %d id is not found", id));
            }

            InsuranceCompany insuranceCompany = companyMapper.updateAllFields(optional.get(), dto);
            InsuranceCompany updated = companyRepository.save(insuranceCompany);

            return HttpApiResponse.<InsuranceCompanyDto>builder()
                    .status(HttpStatus.OK)
                    .content(companyMapper.toDto(updated))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<InsuranceCompanyDto> deleteInsuranceCompanyById(Long id) {
        try {
            Optional<InsuranceCompany> optional = companyRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Insurance Company with %d id is not found", id));
            }

            optional.get().setDeletedAt(LocalDateTime.now());
            companyRepository.save(optional.get());

            return HttpApiResponse.<InsuranceCompanyDto>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<InsuranceCompanyDto>> getAllInsuranceCompanies() {
        try {
            List<InsuranceCompany> companies = companyRepository.findAllByDeletedAtIsNull();
            if (companies.isEmpty()){
                throw new ContentNotFoundException("Companies list is empty");
            }

            return HttpApiResponse.<List<InsuranceCompanyDto>>builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .content(companyMapper.toDtoList(companies))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
