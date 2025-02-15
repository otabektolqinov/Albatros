package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import com.example.albartros.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("news/")
public class NewsController {
    private final NewsService newsService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpApiResponse<NewsDto> createNews(
            @Valid @RequestPart("dto") NewsDto dto,
            @RequestPart("file") MultipartFile file) {
        return this.newsService.createNews(dto, file);
    }

    @GetMapping("/{id}")
    public HttpApiResponse<NewsDto> getNewsById(@PathVariable Long id) {
        return this.newsService.getNewsById(id);
    }

    @GetMapping("/get-all")
    public HttpApiResponse<List<NewsDto>> getAllNews() {
        return this.newsService.getAllNews();
    }

    @PutMapping("/{id}")
    public HttpApiResponse<NewsDto> updateNewsById(@PathVariable Long id, @RequestBody NewsDto dto) {
        return this.newsService.updateNewsById(id, dto);
    }

    @PutMapping("/{id}/updateImage")
    public HttpApiResponse<NewsDto> updateNewsImage(@PathVariable Long id, @RequestBody MultipartFile file) throws IOException {
        return this.newsService.updateNewsImage(id, file);
    }

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteNewsById(@PathVariable Long id) {
        return this.newsService.deleteNewsById(id);
    }

}
