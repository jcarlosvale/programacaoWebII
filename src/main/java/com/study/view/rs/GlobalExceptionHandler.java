package com.study.view.rs;

import com.study.domain.dto.ErrorResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException (final ClassNotFoundException exception){
        return ResponseEntity.ok(exception.getMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            final MethodArgumentNotValidException exception){

        log.error(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.createFromValidation(exception));

    }
}
