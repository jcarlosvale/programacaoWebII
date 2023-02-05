package com.study.view.rs;

import com.study.dto.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;


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

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(final EntityNotFoundException exception) {

        log.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorMessage> handleIllegalStateException(final IllegalStateException exception) {

        log.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorMessage.builder()
                        .message(exception.getMessage())
                        .build());
    }
}