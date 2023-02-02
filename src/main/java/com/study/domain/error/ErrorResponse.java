package com.study.domain.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class ErrorResponse {

    private String message;

    private Collection<ErrorMessage> erros;

    public static ErrorResponse createFormValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        var validations = methodArgumentNotValidException.getFieldErrors().stream().
                map(cv -> new ErrorMessage(cv.getField(), cv.getDefaultMessage())).collect(Collectors.toList());

        return new ErrorResponse("Validations Errors: " ,validations);
    }

}
