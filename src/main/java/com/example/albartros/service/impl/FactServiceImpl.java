package com.example.albartros.service.impl;

import com.example.albartros.dto.FactDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.model.Facts;
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
    private final FactMapper factMapper;

    @Override
    public HttpApiResponse<FactDto> createFact(FactDto dto) {
        //check country
        try {
            return HttpApiResponse.<FactDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.factMapper.toDto(
                            this.factRepository.saveAndFlush(
                                    this.factMapper.toEntity(dto))))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<FactDto> getFactById(Long id) {
        try {
            if (this.factRepository.existsById(id)) {
                Optional<Facts> optional = this.factRepository.findByIdAndDeletedAtIsNull(id);
                if (optional.isPresent()) {
                    return HttpApiResponse.<FactDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.factMapper.toDto(optional.get()))
                            .build();
                }
            }
            return HttpApiResponse.<FactDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<List<FactDto>> getAllFacts() {
        if (this.factRepository.findAllByDeletedAtIsNull().isEmpty()) {
            return HttpApiResponse.<List<FactDto>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Facts List is empty")
                    .build();
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
            if (this.factRepository.existsById(id)) {
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
            return HttpApiResponse.<FactDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public HttpApiResponse<String> deleteFactById(Long id) {
        try {
            if (this.factRepository.existsById(id)) {
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
            return HttpApiResponse.<String>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Fact Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<Facts> getAllFactList() {
        return this.factRepository.findAllByDeletedAtIsNull();
    }
}
