package com.example.albartros.service.impl;

import com.example.albartros.dto.AbbreviationCategoryDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.AbbreviationCategory;
import com.example.albartros.repository.AbbreviationCategoryRepository;
import com.example.albartros.service.AbbreviationCategoryService;
import com.example.albartros.service.mapper.AbbreviationCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AbbreviationCategoryServiceImpl implements AbbreviationCategoryService {

    private final AbbreviationCategoryRepository categoryRepository;
    private final AbbreviationCategoryMapper categoryMapper;

    @Override
    public HttpApiResponse<AbbreviationCategoryDto> createAbbreviationCategory(AbbreviationCategoryDto dto) {
        try {
            AbbreviationCategory entity = categoryMapper.toEntity(dto);
            AbbreviationCategory category = categoryRepository.save(entity);

            return HttpApiResponse.<AbbreviationCategoryDto>builder()
                    .status(HttpStatus.CREATED)
                    .content(categoryMapper.toDto(category))
                    .message("OK")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationCategoryDto> getAbbrCategoryById(Long id) {
        try {
            Optional<AbbreviationCategory> category = categoryRepository.findByIdAndDeletedAtIsNull(id);
            if (category.isEmpty()) {
                throw new ContentNotFoundException(String.format("Abbreviation Category with %d id", id));
            }

            AbbreviationCategoryDto dto = categoryMapper.toDto(category.get());

            return HttpApiResponse.<AbbreviationCategoryDto>builder()
                    .content(dto)
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationCategoryDto> updateAbbrCategoryById(Long id, AbbreviationCategoryDto dto) {
        try {
            Optional<AbbreviationCategory> optional = categoryRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                throw new ContentNotFoundException(String.format("Abbreviation Category with %d id", id));
            }

            AbbreviationCategory abbreviationCategory = categoryMapper.updateAllFields(optional.get(), dto);
            AbbreviationCategory category = categoryRepository.save(abbreviationCategory);

            return HttpApiResponse.<AbbreviationCategoryDto>builder()
                    .status(HttpStatus.OK)
                    .content(categoryMapper.toDto(category))
                    .message("Successfully updated")
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<AbbreviationCategoryDto> deleteAbbrCategoryById(Long id) {
        try {
            Optional<AbbreviationCategory> optional = categoryRepository.findByIdAndDeletedAtIsNull(id);
            if (optional.isEmpty()) {
                throw new ContentNotFoundException(String.format("Abbreviation Category with %d id", id));
            }

            AbbreviationCategory abbreviationCategory = optional.get();
            abbreviationCategory.setDeletedAt(LocalDateTime.now());

            categoryRepository.save(abbreviationCategory);
            return HttpApiResponse.<AbbreviationCategoryDto>builder()
                    .message("Successfully deleted")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
