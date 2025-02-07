package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDto {

    private Long id;
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;
    @NotBlank(message = "description cannot be blank")
    @NotNull(message = "description cannot be null")
    private String description;
    @NotBlank(message = "imageUrl cannot be blank")
    @NotNull(message = "imageUrl cannot be null")
    private String imageUrl;
    @NotNull(message = "countryId cannot be null")
    private Long countryId;

}
