package com.example.albartros.dto;

import com.example.albartros.model.News;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinationDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private Long countryId;
    private List<NewsDto> newsList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
