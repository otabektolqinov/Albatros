package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import com.example.albartros.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("news/")
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public HttpApiResponse<NewsDto> createNews(@RequestBody NewsDto dto) {
        return this.newsService.createNews(dto);
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

    @DeleteMapping("/{id}")
    public HttpApiResponse<String> deleteNewsById(@PathVariable Long id) {
        return this.newsService.deleteNewsById(id);
    }

}
