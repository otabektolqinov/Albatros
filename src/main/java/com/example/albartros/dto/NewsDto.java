package com.example.albartros.dto;

import lombok.*;

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
}
