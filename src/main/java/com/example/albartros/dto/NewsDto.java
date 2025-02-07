package com.example.albartros.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewsDto {
    private Long id;
    private String headline;
    private String text;
    private String image_url;
    private Long destinationId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
