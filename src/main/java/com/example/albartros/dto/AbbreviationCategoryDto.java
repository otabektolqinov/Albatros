package com.example.albartros.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbbreviationCategoryDto {

    private Long id;
    private String name;
    private List<AbbreviationDto> abbreviations;

}
