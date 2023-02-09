package com.study.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.web.bind.*;

import java.util.*;
import java.util.stream.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ErrorResponseDTO {
    private String message;
    private Collection<ErrorMessageDTO> errors;

    public static ErrorResponseDTO createFromValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        var violations =
                methodArgumentNotValidException
                .getFieldErrors()
                .stream()
                .map(cv -> new ErrorMessageDTO(cv.getField(), cv.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResponseDTO("Validation Errors", violations);
    }
}