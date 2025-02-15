package com.example.albartros.controller;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import com.example.albartros.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create News", description = "Create news")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "News Created Successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Error While creating news"
            )
    })
    public HttpApiResponse<NewsDto> createNews(
            @Valid @RequestPart("dto") NewsDto dto,
            @RequestPart("file") MultipartFile file) {
        return this.newsService.createNews(dto, file);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find News", description = "Find news by id")
    @ApiResponse(responseCode = "200",description = "News retrieved successfully")
    @ApiResponse(responseCode = "404",description = "News not found")
    public HttpApiResponse<NewsDto> getNewsById(@PathVariable Long id) {
        return this.newsService.getNewsById(id);
    }

    @GetMapping("/get-all")
    @Operation(summary = "Find NewsList", description = "Find news list")
    @ApiResponse(responseCode = "200",description = "NewsList retrieved successfully")
    @ApiResponse(responseCode = "404",description = "News not found")
    public HttpApiResponse<List<NewsDto>> getAllNews() {
        return this.newsService.getAllNews();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update News", description = "Update news by id")
    @ApiResponse(responseCode = "200",description = "News Updated successfully")
    @ApiResponse(responseCode = "404",description = "News not found")
    @ApiResponse(responseCode = "400",description = "Error while updating news")
    public HttpApiResponse<NewsDto> updateNewsById(@PathVariable Long id, @RequestBody NewsDto dto) {
        return this.newsService.updateNewsById(id, dto);
    }

    @PutMapping("/{id}/updateImage")
    @Operation(summary = "Update NewsImage", description = "Update newsImage by id")
    @ApiResponse(responseCode = "200",description = "NewsImage Updated successfully")
    @ApiResponse(responseCode = "404",description = "News not found")
    @ApiResponse(responseCode = "400",description = "Error while updating news")
    public HttpApiResponse<NewsDto> updateNewsImage(@PathVariable Long id, @RequestBody MultipartFile file) throws IOException {
        return this.newsService.updateNewsImage(id, file);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete News", description = "Delete news by id")
    @ApiResponse(responseCode = "200",description = "News deleted successfully")
    @ApiResponse(responseCode = "404",description = "News not found")
    @ApiResponse(responseCode = "400",description = "Error while updating news")
    public HttpApiResponse<String> deleteNewsById(@PathVariable Long id) {
        return this.newsService.deleteNewsById(id);
    }

}
