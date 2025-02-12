package com.example.albartros.service.impl;

import com.example.albartros.dto.CoverageDetailsDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.CoverageDetails;
import com.example.albartros.repository.CoverageDetailsRepository;
import com.example.albartros.service.CoverageDetailsService;
import com.example.albartros.service.mapper.CoverageDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CoverageDetailsServiceImpl implements CoverageDetailsService {

    private final CoverageDetailsRepository detailsRepository;
    private final CoverageDetailsMapper detailsMapper;

    @Override
    public HttpApiResponse<CoverageDetailsDto> createCoverageDetails(CoverageDetailsDto dto) {
        try {
            CoverageDetails entity = detailsMapper.toEntity(dto);
            CoverageDetails saved = detailsRepository.save(entity);

            return HttpApiResponse.<CoverageDetailsDto>builder()
                    .status(HttpStatus.CREATED)
                    .content(detailsMapper.toDto(saved))
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CoverageDetailsDto> getCoverageDetailsById(Long id) {
        try {
            Optional<CoverageDetails> optional = detailsRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("CoverageDetails with %d id is not found", id));
            }

            return HttpApiResponse.<CoverageDetailsDto>builder()
                    .content(detailsMapper.toDto(optional.get()))
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CoverageDetailsDto> updateCoverageDetailsById(Long id, CoverageDetailsDto dto) {
        try {
            Optional<CoverageDetails> optional = detailsRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("CoverageDetails with %d id is not found", id));
            }

            CoverageDetails coverageDetails = detailsMapper.updateAllFields(optional.get(), dto);
            CoverageDetails saved = detailsRepository.save(coverageDetails);

            return HttpApiResponse.<CoverageDetailsDto>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(detailsMapper.toDto(saved))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<CoverageDetailsDto> deleteCoverageDetailsById(Long id) {
        Optional<CoverageDetails> optional = detailsRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("CoverageDetails with %d id is not found", id));
        }

        CoverageDetails coverageDetails = optional.get();
        coverageDetails.setDeletedAt(LocalDateTime.now());
        CoverageDetails saved = detailsRepository.save(coverageDetails);

        return HttpApiResponse.<CoverageDetailsDto>builder()
                .content(detailsMapper.toDto(saved))
                .status(HttpStatus.OK)
                .message("Successfully deleted")
                .build();
    }
}
