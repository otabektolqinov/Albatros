package com.example.albartros.dto;

import com.example.albartros.model.News;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DestinationDto {
    private Long id;
    private String name;
    private String description;
    private String image_url;
    private Long countryId;
    private List<News> newsList;
}
