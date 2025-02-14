package com.example.albartros.service.impl;

import com.example.albartros.dto.AbbreviationDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Abbreviation;
import com.example.albartros.repository.AbbreviationRepository;
import com.example.albartros.service.AbbreviationService;
import com.example.albartros.service.mapper.AbbreviationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AbbreviationServiceImpl implements AbbreviationService {

    private final AbbreviationMapper abbreviationMapper;
    private final AbbreviationRepository abbreviationRepository;

    @Override
    public HttpApiResponse<AbbreviationDto> createAbbreviation(AbbreviationDto dto) {
        try {
            Abbreviation entity = abbreviationMapper.toEntity(dto);

            Abbreviation saved = abbreviationRepository.save(entity);

            return HttpApiResponse.<AbbreviationDto>builder()
                    .status(HttpStatus.CREATED)
                    .content(abbreviationMapper.toDto(saved))
                    .message("OK")
                    .build();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationDto> getAbbreviationById(Long id) {
        try {
            Optional<Abbreviation> optional = abbreviationRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("There is no abbreviation with %d id", id));
            }

            return HttpApiResponse.<AbbreviationDto>builder()
                    .content(abbreviationMapper.toDto(optional.get()))
                    .status(HttpStatus.OK)
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationDto> updateAbbreviationById(Long id, AbbreviationDto dto) {
        try {
            Optional<Abbreviation> optional = abbreviationRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("There is no abbreviation with %d id", id));
            }

            Abbreviation abbreviation = abbreviationMapper.updateAllFields(optional.get(), dto);

            return HttpApiResponse.<AbbreviationDto>builder()
                    .status(HttpStatus.OK)
                    .content(abbreviationMapper.toDto(abbreviation))
                    .message("Successfully updated")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationDto> deleteAbbreviationById(Long id) {
        Optional<Abbreviation> optional = abbreviationRepository.findById(id);
        if (optional.isEmpty()){
            throw new ContentNotFoundException(String.format("There is no abbreviation with %d id", id));
        }

        Abbreviation abbreviation = optional.get();
        abbreviation.setDeletedAt(LocalDateTime.now());
        abbreviationRepository.save(abbreviation);

        return HttpApiResponse.<AbbreviationDto>builder()
                .message("Successfully deleted")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviationsByCategory(Long categoryId) {
        try {
            List<Abbreviation> allByCategoryId = abbreviationRepository.findAllByCategory_Id(categoryId);
            if (allByCategoryId.isEmpty()){
                throw new ContentNotFoundException("There is no abbreviation in this category");
            }

            List<AbbreviationDto> dtoList = abbreviationMapper.toDtoList(allByCategoryId);

            return HttpApiResponse.<List<AbbreviationDto>>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(dtoList)
                    .build();
        } catch (DatabaseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<AbbreviationDto>> getAllAbbreviations() {
        try {
            List<Abbreviation> all = abbreviationRepository.findAllByDeletedAtIsNull();
            if (all.isEmpty()){
                throw new ContentNotFoundException("Abbreviation List is empty");
            }

            List<AbbreviationDto> dtoList = abbreviationMapper.toDtoList(all);

            return HttpApiResponse.<List<AbbreviationDto>>builder()
                    .content(dtoList)
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
