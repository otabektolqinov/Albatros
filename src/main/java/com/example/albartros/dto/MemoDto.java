package com.example.albartros.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoDto {

    private Long id;
    private String title;
    private String fileUrl;
    private Long countryId;

}
