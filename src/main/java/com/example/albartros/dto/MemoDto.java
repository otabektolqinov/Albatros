package com.example.albartros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoDto {

    private Long id;
    @NotBlank(message = "title cannot be blank")
    @NotNull(message = "title cannot be null")
    private String title;
    @NotBlank(message = "fileUrl cannot be blank")
    @NotNull(message = "fileUrl cannot be null")
    private String fileUrl;
    @NotNull(message = "countryId cannot be null")
    private Long countryId;

}
