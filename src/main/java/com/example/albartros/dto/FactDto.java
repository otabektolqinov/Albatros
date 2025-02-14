package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FactDto {
    private Long id;
    @NotBlank(message = "Description must not be blank")
    @NotEmpty(message = "Description must not be empty")
    private String description;
    @NotNull(message = "CountryId must not be null")
    private Long countryId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
