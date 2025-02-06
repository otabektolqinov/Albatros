package com.example.albartros.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T content;
    private List<ErrorDto> errorDto;
}
