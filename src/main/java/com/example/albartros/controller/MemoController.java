package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.MemoDto;
import com.example.albartros.service.MemoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("memo/")
public class MemoController {

    private final MemoService memoService;

    @Operation(summary = "Create a new Memo", description = "Add a new Memo")
    @PostMapping
    public HttpApiResponse<MemoDto> createMemo(@RequestBody @Valid MemoDto dto){
        return this.memoService.createMemo(dto);
    }

    @Operation(summary = "Get a Memo by ID", description = "Fetch a memo by ID")
    @GetMapping("/{id}")
    public HttpApiResponse<MemoDto> getMemoById(@PathVariable("id") Long id){
        return this.memoService.getMemoById(id);
    }

    @Operation(summary = "Update a memo by Id", description = "Update a memo using its ID")
    @PutMapping("/{id}")
    public HttpApiResponse<MemoDto> updateMemoById(@PathVariable("id") Long id,
                                                   @RequestBody MemoDto dto){
        return this.memoService.updateMemoById(id, dto);
    }

    @Operation(summary = "Delete a memo by ID", description = "Delete a memo using its ID")
    @DeleteMapping("/{id}")
    public HttpApiResponse<MemoDto> deleteMemoById(@PathVariable("id") Long id){
        return this.memoService.deleteMemoById(id);
    }

    @Operation(summary = "Get all memos by Country", description = "Fetch all memos by Country ID")
    @GetMapping("/get-all-by-country/{id}")
    public HttpApiResponse<List<MemoDto>> getAllMemoByCountry(@PathVariable("id") Long id){
        return this.memoService.getAllMemoByCountry(id);
    }

}
