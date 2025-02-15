package com.example.albartros.dto;

import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbbreviationDto {

    private Long id;
    @NotBlank(message = "abbreviation short form cannot be null, blank or empty")
    private String shortForm;
    @NotBlank(message = "abbreviation description cannot be null, blank or empty")
    private String description;
    @Positive(message = "categoryId cannot be zero or negative number")
    private Long categoryId;

}
