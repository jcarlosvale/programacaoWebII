package com.study.domain.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
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

    public static ErrorResponse sqlIntegrityConstraintViolation(JdbcSQLIntegrityConstraintViolationException jdbcSQLIntegrityConstraintViolationException) {
        var cv  = jdbcSQLIntegrityConstraintViolationException;

        var violation = new ErrorMessage("", cv.getMessage());
        var violations = new ArrayList<ErrorMessage>();
        violations.add(violation);

        return new ErrorResponse("Validation Errors", violations);

    }
}