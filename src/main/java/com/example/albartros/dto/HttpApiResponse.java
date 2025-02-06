package com.example.albartros.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HttpApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T content;
    private ErrorDto errorDto;
}
