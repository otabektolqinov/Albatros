package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbbreviationCategoryDto {

    private Long id;
    @NotBlank(message = "abbreviation category name cannot be blank, null or empty")
    private String name;
    private List<AbbreviationDto> abbreviations;

}
