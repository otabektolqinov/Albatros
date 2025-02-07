package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.MemoDto;
import com.example.albartros.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("memo")
public class MemoController {

    private final MemoService memoService;

    @PostMapping
    public HttpApiResponse<MemoDto> createMemo(@RequestBody @Valid MemoDto dto){
        return this.memoService.createMemo(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<MemoDto> getMemoById(@PathVariable("id") Long id){
        return this.memoService.getMemoById(id);
    }

    @PutMapping("/{id}")
    public HttpApiResponse<MemoDto> updateMemoById(@PathVariable("id") Long id,
                                                   @RequestBody MemoDto dto){
        return this.memoService.updateMemoById(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<MemoDto> deleteMemoById(@PathVariable("id") Long id){
        return this.memoService.deleteMemoById(id);
    }

    @GetMapping("/get-all-by-country/{id}")
    public HttpApiResponse<List<MemoDto>> getAllMemoByCountry(@PathVariable("id") Long id){
        return this.memoService.getAllMemoByCountry(id);
    }

}
