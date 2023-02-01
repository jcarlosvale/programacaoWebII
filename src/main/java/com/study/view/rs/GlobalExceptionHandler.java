package com.study.view.rs;

import com.study.domain.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> HandlerMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception){
        log.error(exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.createFromValidation(exception));
    }
}
