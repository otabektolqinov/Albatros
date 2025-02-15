package com.example.albartros.dto;

import com.example.albartros.model.News;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Name must not be blank")
    @NotEmpty(message = "Message must not be empty")
    private String name;
    @NotBlank(message = "Description must not be blank")
    @NotEmpty(message = "Description must not be empty")
    private String description;
    private String image_url;
    @NotNull(message = "CountryId must not be null")
    private Long countryId;
    private List<NewsDto> newsList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
