package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.MemoDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Country;
import com.example.albartros.model.Memo;
import com.example.albartros.repository.CountryRepository;
import com.example.albartros.repository.MemoRepository;
import com.example.albartros.service.MemoService;
import com.example.albartros.service.mapper.MemoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final CountryRepository countryRepository;
    private final MemoRepository memoRepository;
    private final MemoMapper memoMapper;

    @Override
    public HttpApiResponse<MemoDto> createMemo(MemoDto dto) {
        try {
            if (!countryRepository.existsByIdAndDeletedAtIsNull(dto.getCountryId())){
                throw new ContentNotFoundException(String.format("There is no country with %d id", dto.getCountryId()));
            }
            Memo entity = memoMapper.toEntity(dto);
            Country country = countryRepository.findByIdAndDeletedAtIsNull(dto.getCountryId()).get();
            entity.setCountry(country);
            Memo saved = memoRepository.save(entity);
            return HttpApiResponse.<MemoDto>builder()
                    .content(memoMapper.toDto(saved))
                    .message("OK")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<MemoDto> getMemoById(Long id) {
        try {
            Optional<Memo> optional = memoRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Memo with %d id is not found", id));
            }
            MemoDto dto = memoMapper.toDto(optional.get());
            return HttpApiResponse.<MemoDto>builder()
                    .status(HttpStatus.OK)
                    .message("OK")
                    .content(dto)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<MemoDto> updateMemoById(Long id, MemoDto dto) {
        try {
            Optional<Memo> optional = memoRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Memo with %d id is not found", id));
            }
            Memo memo = optional.get();
            Memo updated = memoMapper.updatedAllFields(memo, dto);

            return HttpApiResponse.<MemoDto>builder()
                    .content(memoMapper.toDto(updated))
                    .message("OK")
                    .status(HttpStatus.OK)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<MemoDto> deleteMemoById(Long id) {
        try {
            Optional<Memo> optional = memoRepository.findById(id);
            if (optional.isEmpty()){
                throw new ContentNotFoundException(String.format("Memo with %d id is not found", id));
            }
            Memo memo = optional.get();
            memo.setDeletedAt(LocalDateTime.now());
            memoRepository.save(memo);
            return HttpApiResponse.<MemoDto>builder()
                    .status(HttpStatus.OK)
                    .message("Successfully deleted")
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public HttpApiResponse<List<MemoDto>> getAllMemoByCountry(Long id) {
        try {
            if (!countryRepository.existsByIdAndDeletedAtIsNull(id)){
                throw new ContentNotFoundException(String.format("There is no country with %d id", id));
            }
            List<Memo> byCountryId = memoRepository.findByCountry_Id(id);
            if (byCountryId.isEmpty()){
                throw new ContentNotFoundException("There is no memo associated with this country");
            }
            List<MemoDto> dtoList = memoMapper.toDtoList(byCountryId);

            return HttpApiResponse.<List<MemoDto>>builder()
                    .message("OK")
                    .status(HttpStatus.OK)
                    .content(dtoList)
                    .build();
        } catch (ContentNotFoundException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
