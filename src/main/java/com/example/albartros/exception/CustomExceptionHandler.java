package com.example.albartros.exception;

import com.example.albartros.dto.HttpApiResponse;
import com.example.albartros.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<HttpApiResponse<Void>> methodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        List<ErrorDto> errors = new ArrayList<>();

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
            errors.add(new ErrorDto(fieldName,
                    String.format("%s, Rejection value %s", message, rejectionValue))
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(HttpApiResponse.<Void>builder()
                        .message("Validation failed!")
                        .errorDto(errors)
                        .build());
    }

    @ExceptionHandler(value = ContentNotFoundException.class)
    public ResponseEntity<HttpApiResponse<Void>> contentNotFoundException(
            ContentNotFoundException e
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(HttpApiResponse.<Void>builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<HttpApiResponse<Void>> databaseException(
            DatabaseException e
    ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(HttpApiResponse.<Void>builder()
                        .message(e.getMessage())
                        .build());
    }


}
