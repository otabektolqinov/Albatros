package com.example.albartros.service.impl;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.NewsDto;
import com.example.albartros.exception.ContentNotFoundException;
import com.example.albartros.exception.DatabaseException;
import com.example.albartros.model.Destination;
import com.example.albartros.model.News;
import com.example.albartros.repository.DestinationRepository;
import com.example.albartros.repository.NewsRepository;
import com.example.albartros.service.NewsService;
import com.example.albartros.service.mapper.NewsMapper;
import com.example.albartros.utils.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final DestinationRepository destinationRepository;
    private final NewsMapper newsMapper;
    private final FileUploadService fileUploadService;


    @Override
    public HttpApiResponse<NewsDto> createNews(NewsDto dto, MultipartFile file) {
        if (this.destinationRepository.findByIdAndDeletedAtIsNull(dto.getDestinationId()).isEmpty()) {
            throw new ContentNotFoundException("Destination does not exist");
        }
        try {
            Optional<Destination> optionalDestination = this.destinationRepository.findByIdAndDeletedAtIsNull(dto.getDestinationId());
            News news = this.newsMapper.toEntity(dto);
            optionalDestination.ifPresent(news::setDestination);
            if (!file.isEmpty()) {
                String fileUrl = fileUploadService.handleFileUpload(file);
                news.setImage_url(fileUrl);
            }
            return HttpApiResponse.<NewsDto>builder()
                    .status(HttpStatus.CREATED)
                    .message("OK")
                    .content(this.newsMapper.toDto(
                            this.newsRepository.saveAndFlush(news)))
                    .build();
        } catch (Exception e) {
            throw new DatabaseException("Unable to create new news");
        }
    }

    @Override
    public HttpApiResponse<NewsDto> getNewsById(Long id) {
        try {
            if (this.newsRepository.existsByIdAndDeletedAtIsNull(id)) {
                Optional<News> optionalNews = this.newsRepository.findByIdAndDeletedAtIsNull(id);
                if (optionalNews.isPresent()) {
                    return HttpApiResponse.<NewsDto>builder()
                            .status(HttpStatus.OK)
                            .message("OK")
                            .content(this.newsMapper.toDto(optionalNews.get()))
                            .build();
                }
            }
            throw new ContentNotFoundException("News not found");
        } catch (Exception e) {
            throw new ContentNotFoundException("Unable to get new news");
        }
    }

    @Override
    public HttpApiResponse<List<NewsDto>> getAllNews() {
        if (this.newsRepository.findAllByDeletedAtIsNull().isEmpty()) {
            throw new ContentNotFoundException("News does not exist");
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
            if (this.newsRepository.existsByIdAndDeletedAtIsNull(id)) {
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
            throw new ContentNotFoundException("News not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to update new news");
        }

    }

    @Override
    public HttpApiResponse<NewsDto> updateNewsImage(Long id, MultipartFile file) throws IOException {
        News news = this.newsRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(()
                -> new ContentNotFoundException("News not found"));
        String imageUrl = fileUploadService.handleFileUpload(file);
        news.setImage_url(imageUrl);
        return HttpApiResponse.<NewsDto>builder()
                .status(HttpStatus.OK)
                .message("OK")
                .content(this.newsMapper.toDto(news))
                .build();
    }

    @Override
    public HttpApiResponse<String> deleteNewsById(Long id) {
        try {
            if (this.newsRepository.existsByIdAndDeletedAtIsNull(id)) {
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
            throw new ContentNotFoundException("News not found");
        } catch (Exception e) {
            throw new DatabaseException("Unable to delete news");
        }

    }

    private List<News> getAllNewsList() {
        return this.newsRepository.findAllByDeletedAtIsNull();
    }

}
