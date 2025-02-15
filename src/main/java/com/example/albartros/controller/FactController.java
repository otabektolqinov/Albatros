package com.example.albartros.controller;

import com.example.albartros.dto.FactDto;
import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.service.FactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("fact/")
public class FactController {
    private final FactService factService;

    @PostMapping
    public HttpApiResponse<FactDto> createFact(@RequestBody @Valid FactDto dto) {
        return this.factService.createFact(dto);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<FactDto> getFactById(@PathVariable Long id) {
        return this.factService.getFactById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<FactDto>> getAllFacts() {
        return this.factService.getAllFacts();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<FactDto> updateFact(@PathVariable Long id, @RequestBody FactDto dto) {
        return this.factService.updateFact(id, dto);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteFactById(@PathVariable Long id) {
        return this.factService.deleteFactById(id);
    }
}
