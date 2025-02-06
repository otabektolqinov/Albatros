package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.MemoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemoService {

    HttpApiResponse<MemoDto> createMemo(MemoDto dto);
    HttpApiResponse<MemoDto> getMemoById(Long id);
    HttpApiResponse<MemoDto> updateMemoById(Long id, MemoDto dto);
    HttpApiResponse<MemoDto> deleteMemoById(Long id);
    HttpApiResponse<List<MemoDto>> getAllMemoByCountry(Long id);

}
