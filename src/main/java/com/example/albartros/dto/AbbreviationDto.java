package com.example.albartros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbbreviationDto {

    private Long id;
    private String shortForm;
    private String description;
    private Long categoryId;

}
