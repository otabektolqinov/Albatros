package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import com.example.albartros.model.Destination;
import com.example.albartros.model.News;
import com.example.albartros.repository.DestinationRepository;
import com.example.albartros.repository.NewsRepository;
import com.example.albartros.service.NewsService;
import com.example.albartros.service.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final DestinationRepository destinationRepository;
    private final NewsMapper newsMapper;


    @Override
    public HttpApiResponse<NewsDto> createNews(NewsDto dto) {
        if (this.destinationRepository.findByIdAndDeletedAtIsNull(dto.getDestinationId()).isEmpty()) {
            return HttpApiResponse.<NewsDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Destination not found")
                    .build();
        }
        try {
            Optional<Destination> optionalDestination = this.destinationRepository.findById(dto.getDestinationId());
            News news = this.newsMapper.toEntity(dto);
            optionalDestination.ifPresent(news::setDestination);
            return HttpApiResponse.<NewsDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.newsMapper.toDto(
                            this.newsRepository.saveAndFlush(news)))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<NewsDto> getNewsById(Long id) {
        try {
            if (this.newsRepository.existsById(id)) {
                Optional<News> optionalNews = this.newsRepository.findByIdAndDeletedAtIsNull(id);
                if (optionalNews.isPresent()) {
                    return HttpApiResponse.<NewsDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.newsMapper.toDto(optionalNews.get()))
                            .build();
                }
            }
            return HttpApiResponse.<NewsDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("News Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public HttpApiResponse<List<NewsDto>> getAllNews() {
        if (this.newsRepository.findAllByDeletedAtIsNull().isEmpty()) {
            return HttpApiResponse.<List<NewsDto>>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("News Not Found")
                    .build();
        }
        return HttpApiResponse.<List<NewsDto>>builder()
                .status(HttpStatus.OK)
                .message("OK")
                .content(this.newsMapper.toDtoList(getAllNewsList()))
                .build();
    }

    @Override
    public HttpApiResponse<NewsDto> updateNewsById(Long id, NewsDto dto) {
        try {
            if (this.newsRepository.existsById(id)) {
                Optional<News> optionalNews = this.newsRepository.findByIdAndDeletedAtIsNull(id);
                if (optionalNews.isPresent()) {
                    News updateNews = this.newsMapper.updateNews(optionalNews.get(), dto);
                    this.newsRepository.saveAndFlush(updateNews);
                    return HttpApiResponse.<NewsDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.newsMapper.toDto(updateNews))
                            .build();
                }
            }
            return HttpApiResponse.<NewsDto>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("News Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public HttpApiResponse<String> deleteNewsById(Long id) {
        try {
            if (this.newsRepository.existsById(id)) {
                Optional<News> optionalNews = this.newsRepository.findByIdAndDeletedAtIsNull(id);
                if (optionalNews.isPresent()) {
                    optionalNews.get().setDeletedAt(LocalDateTime.now());
                    this.newsRepository.save(optionalNews.get());
                    return HttpApiResponse.<String>builder()
                            .status(HttpStatus.OK)
                            .message("News Deleted")
                            .build();
                }
            }
            return HttpApiResponse.<String>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("News Not Found")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private List<News> getAllNewsList() {
        return this.newsRepository.findAllByDeletedAtIsNull();
    }
}
