package com.example.albartros.service.impl;

import com.example.albartros.dto.FactDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.model.Facts;
import com.example.albartros.repository.CountryRepository;
import com.example.albartros.repository.FactRepository;
import com.example.albartros.service.FactService;
import com.example.albartros.service.mapper.FactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FactServiceImpl implements FactService {
    private final FactRepository factRepository;
    private final CountryRepository countryRepository;
    private final FactMapper factMapper;

    @Override
    public HttpApiResponse<FactDto> createFact(FactDto dto) {
        if (!countryRepository.existsByIdAndDeletedAtIsNull(dto.getCountryId())) {
            throw new ContentNotFoundException("Country not found");
        }
        try {
            Optional<Country> country = countryRepository.findByIdAndDeletedAtIsNull(dto.getCountryId());

            if (country.isPresent()) {
                Facts entity = this.factMapper.toEntity(dto);

                entity.setCountry(country.get());

                this.factRepository.save(entity);
                return HttpApiResponse.<FactDto>builder()
                        .status(HttpStatus.CREATED)
                        .message("OK")
                        .content(this.factMapper.toDto(entity))
                        .build();
            }
            throw new ContentNotFoundException("Country not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to create fact");
        }
    }

    @Override
    public HttpApiResponse<FactDto> getFactById(Long id) {
        try {
            if (this.factRepository.existsByIdAndDeletedAtIsNull(id)) {

                Optional<Facts> optional = this.factRepository.findByIdAndDeletedAtIsNull(id);

                if (optional.isPresent()) {
                    return HttpApiResponse.<FactDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.factMapper.toDto(optional.get()))
                            .build();
                }
            }

            throw new ContentNotFoundException("Facts not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get fact by id");
        }
    }

    @Override
    public HttpApiResponse<List<FactDto>> getAllFacts() {
        if (this.factRepository.findAllByDeletedAtIsNull().isEmpty()) {
            throw new ContentNotFoundException("Facts not found");
        }
        return HttpApiResponse.<List<FactDto>>builder()
                .status(HttpStatus.OK)
                .message("OK")
                .content(this.factMapper.toDtoList(getAllFactList()))
                .build();
    }

    @Override
    public HttpApiResponse<FactDto> updateFact(Long id, FactDto dto) {
        try {
            if (this.factRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Facts> optional = this.factRepository.findByIdAndDeletedAtIsNull(id);
                if (optional.isPresent()) {
                    Facts updateFact = this.factMapper.updateFact(dto, optional.get());
                    this.factRepository.save(updateFact);
                    return HttpApiResponse.<FactDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.factMapper.toDto(updateFact))
                            .build();
                }
            }

            throw new ContentNotFoundException("Facts not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get fact by id");
        }

    }

    @Override
    public HttpApiResponse<String> deleteFactById(Long id) {
        try {
            if (this.factRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<Facts> optional = this.factRepository.findByIdAndDeletedAtIsNull(id);
                if (optional.isPresent()) {
                    optional.get().setDeletedAt(LocalDateTime.now());
                    this.factRepository.save(optional.get());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("Fact deleted Successfully")
                            .build();
                }
            }
            throw new ContentNotFoundException("Fact not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to update fact by id");
        }
    }

    public List<Facts> getAllFactList() {
        return this.factRepository.findAllByDeletedAtIsNull();
    }
}
