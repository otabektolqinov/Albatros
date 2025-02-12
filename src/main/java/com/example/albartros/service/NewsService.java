package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface NewsService {

    HttpApiResponse<NewsDto> createNews(NewsDto dto, MultipartFile file);

    HttpApiResponse<NewsDto> getNewsById(Long id);

    HttpApiResponse<List<NewsDto>> getAllNews();

    HttpApiResponse<NewsDto> updateNewsById(Long id, NewsDto dto);

    HttpApiResponse<String> deleteNewsById(Long id);

    HttpApiResponse<NewsDto> updateNewsImage(Long id, MultipartFile file) throws IOException;
}
