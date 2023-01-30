package com.study.view.rs;

import com.study.domain.dto.ErrorResponse;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(final MethodArgumentNotValidException exception) {

        log.error(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.createFromValidation(exception));
    }
}