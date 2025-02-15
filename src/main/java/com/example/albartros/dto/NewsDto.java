package com.example.albartros.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewsDto {
    private Long id;
    @NotNull(message = "Headline must not be null")
    private String headline;
    @NotNull(message = "Description must not be null")
    private String text;
    private String image_url;
    private Long destinationId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
