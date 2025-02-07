package com.example.albartros.service.mapper;

import com.example.albartros.dto.NewsDto;
import com.example.albartros.model.News;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {

    public NewsDto toDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .headline(news.getHeadline())
                .text(news.getText())
                .image_url(news.getImage_url())
                .destinationId(news.getDestination().getId())
                .createdAt(news.getCreatedAt())
                .updatedAt(news.getUpdatedAt())
                .deletedAt(news.getDeletedAt())
                .build();
    }

    public News toEntity(NewsDto dto) {
        return News.builder()
                .headline(dto.getHeadline())
                .text(dto.getText())
                .image_url(dto.getImage_url())
                .build();
    }

    public News updateNews(News news, NewsDto dto) {
        if (dto.getHeadline() != null) {
            news.setHeadline(dto.getHeadline());
        }
        if (dto.getText() != null) {
            news.setText(dto.getText());
        }
        if (dto.getImage_url() != null) {
            news.setImage_url(dto.getImage_url());
        }
        return news;
    }

    public List<NewsDto> toDtoList(List<News> newsList) {
        List<NewsDto> newsDtoList = new ArrayList<>();
        for (News news : newsList) {
            newsDtoList.add(toDto(news));
        }
        return newsDtoList;
    }
}
