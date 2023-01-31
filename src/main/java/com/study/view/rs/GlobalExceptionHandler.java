package com.study.view.rs;

import com.study.domain.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final MethodArgumentNotValidException exception){
        return  ResponseEntity.badRequest().body(ErrorResponse.createFromValidation(exception));
    }
}
