package com.study.view.rs;

import com.study.domain.error.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintVialotionException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        return ResponseEntity.badRequest().body(ErrorResponse.createFormValidation(methodArgumentNotValidException));
    }
}
