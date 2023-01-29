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
public class ErrorResponse {

    private String message;
    private Collection<ErrorMessage> errors;

    public static ErrorResponse createFromValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        var violations =
                methodArgumentNotValidException
                .getFieldErrors()
                .stream()
                .map(cv -> new ErrorMessage(cv.getField(), cv.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse("Validation Errors", violations);

    }
}