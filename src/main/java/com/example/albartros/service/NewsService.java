package com.example.albartros.service;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {

    HttpApiResponse<NewsDto> createNews(NewsDto dto);

    HttpApiResponse<NewsDto> getNewsById(Long id);

    HttpApiResponse<List<NewsDto>> getAllNews();

    HttpApiResponse<NewsDto> updateNewsById(Long id, NewsDto dto);

    HttpApiResponse<String> deleteNewsById(Long id);
}
